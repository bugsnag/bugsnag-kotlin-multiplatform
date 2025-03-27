Maze.config.document_server_root = File.realpath("#{__dir__}/../fixtures")

BeforeAll do
  $api_key = "12312312312312312312312312312312"
  Maze.config.receive_no_requests_wait = 10
  Maze.config.receive_requests_wait = 60

  `unzip -d #{Maze.config.document_server_root} /app/build/web.zip`.chomp
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

def get_test_url(scenario, config)
  Maze.config.enforce_bugsnag_integrity = false

  if Maze.config.aws_public_ip
    maze_runner = Maze.public_address
  else
    maze_runner = "#{ENV['HOST']}:9339"
  end

  protocol = Maze.config.https ? 'https' : 'http'

  notify = "#{protocol}://#{maze_runner}/notify"
  sessions = "#{protocol}://#{maze_runner}/sessions"
  logs = "#{protocol}://#{maze_runner}/logs"
  reflect= "#{protocol}://#{maze_runner}/reflect"
  config_query_string = "SCENARIO=#{scenario}&CFG=#{config}&NOTIFY=#{notify}&SESSIONS=#{sessions}"

  uri = URI("#{protocol}://#{maze_runner}/docs/index.html")

  if uri.query
    uri.query += "&#{config_query_string}"
  else
    uri.query = config_query_string
  end

  uri.to_s
end

