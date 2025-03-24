BeforeAll do
  $api_key = "12312312312312312312312312312312"
  Maze.config.receive_no_requests_wait = 10
  Maze.config.receive_requests_wait = 60
end

Before do
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
end
