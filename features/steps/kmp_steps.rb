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
