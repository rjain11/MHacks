require 'firebase'

base_uri = 'https://mhacks2014.firebaseio.com/'
firebase = Firebase::Client.new(base_uri)
response = firebase.update("Record",{:videoid => 'https://youtube.net/assets', :seconds => 10 })
response.success? # => true
