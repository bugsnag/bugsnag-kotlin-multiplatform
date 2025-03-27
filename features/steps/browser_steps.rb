When('I run {string} in the browser') do |scenario_name|
  path = get_test_url(scenario_name, '')
  step("I navigate to the URL \"#{path}\"")
end

When('I run {string} configured as {string} in the browser') do |scenario_name, scenario_config|
  path = get_test_url(scenario_name, scenario_config)
  step("I navigate to the URL \"#{path}\"")
end
