Maze.config.document_server_root = File.realpath("#{__dir__}/../fixtures")

BeforeAll do
  $api_key = "12312312312312312312312312312312"
  Maze.config.receive_no_requests_wait = 10
  Maze.config.receive_requests_wait = 60

  # TODO: Add some kind of mechanism to allow skipping of this locally, when the files already exist
  `unzip -d #{Maze.config.document_server_root} build/web.zip`.chomp
end

Before do
  $scenario_mode = ''
  if Maze.config.farm == :local
    host = "localhost:#{Maze.config.port}"
  elsif Maze.config.farm == :bb
    if Maze.config.aws_public_ip
      host = Maze.public_address
    else
      host = 'local:9339'
    end
  else
    host = 'bs-local.com:9339'
  end
  protocol = Maze.config.https ? 'https' : 'http'
  $sessions_endpoint = "#{protocol}://#{host}/sessions"
  $notify_endpoint = "#{protocol}://#{host}/notify"
end

def get_test_url(scenario, config)
  Maze.config.enforce_bugsnag_integrity = false

  if Maze.config.farm == :local
    maze_runner = "localhost:#{Maze.config.port}"
  elsif Maze.config.farm == :bb
    if Maze.config.aws_public_ip
      maze_runner = Maze.public_address
    else
      maze_runner = "local:#{Maze.config.port}"
    end
  else
    maze_runner = "bs-local.com:#{Maze.config.port}"
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

