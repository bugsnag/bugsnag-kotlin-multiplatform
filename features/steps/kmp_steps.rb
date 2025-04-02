def execute_command(action, scenario_name = '')
  command = {
    action: action,
    scenario_name: scenario_name,
    scenario_mode: $scenario_mode,
    sessions_endpoint: $sessions_endpoint,
    notify_endpoint: $notify_endpoint
  }
  Maze::Server.commands.add command

  # Reset values to defaults
  $scenario_mode = ''
  if Maze.config.farm == :bb
    if Maze.config.aws_public_ip
      $sessions_endpoint = "http://#{Maze.public_address}/sessions"
      $notify_endpoint = "http://#{Maze.public_address}/notify"
    else
      $sessions_endpoint = "http://local:9339/sessions"
      $notify_endpoint = "http://local:9339/notify"
    end
  else
    $sessions_endpoint = 'http://bs-local.com:9339/sessions'
    $notify_endpoint = 'http://bs-local.com:9339/notify'
  end

  # Ensure fixture has read the command
  count = 600
  sleep 0.1 until Maze::Server.commands.size_remaining == 0 || (count -= 1) < 1

  raise 'Test fixture did not GET /command' unless Maze::Server.commands.size_remaining == 0
end

# Waits for up to 10 seconds for the app to stop running.  It seems that Appium doesn't always
# get the state correct (e.g. when backgrounding the app, or on old Android versions), so we
# don't fail if it still says running after the time allowed.
def wait_for_app_state(expected_state)
  manager = Maze::Api::Appium::AppManager.new
  max_attempts = 20
  attempts = 0
  state = manager.state
  until (attempts >= max_attempts) || state == expected_state
    attempts += 1
    state = manager.state
    sleep 0.5
  end
  $logger.warn "App state #{state} instead of #{expected_state} after 10s" unless state == expected_state
  state
end

When("I relaunch the app after a crash") do
  manager = Maze::Api::Appium::AppManager.new
  state = wait_for_app_state :not_running
  if Maze.config.legacy_driver?
    if state != :not_running
      manager.close
    end
    manager.launch
  else
    if state != :not_running
      manager.terminate
    end
    manager.activate
  end
end

When('I run {string}') do |scenario_name|
  execute_command :run_scenario, scenario_name
end

When("I run {string} and relaunch the crashed app") do |scenario_name|
  steps %Q{
    When I run "#{scenario_name}"
    And I relaunch the app after a crash
  }
end

When("I configure Bugsnag for {string}") do |scenario_name|
  execute_command :start_bugsnag, scenario_name
end
