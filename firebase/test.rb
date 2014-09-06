require 'firebase'

base_uri = 'https://mhacks2014.firebaseio.com/'
firebase = Firebase::Client.new(base_uri)
response = firebase.get("mhacks2014",{:videoid => 'https://youtube.net/assets', :seconds => 10 })
puts(response)
response.success? # => true
