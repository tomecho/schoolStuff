var net = require('net');
const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

var client = new net.Socket();
client.connect(12345, '127.0.0.1', function() {
  console.log('Connected');
});

//when we recieve some data
client.on('data', function(data) {
  console.log('Received: ' + data);
});

client.on('close', function() {
  console.log('Connection closed');
});

function askAndRequest() {
  //accept question
  rl.question('type stop or input request for server, format x,y,oper', (answer) => {
    if(answer.match(/stop(\n)*/)) {
      console.log('quiting at request');
      client.destroy();
      return rl.close();
    }
    console.log(`sending ${answer}`);
    var buf = new Buffer(answer);
    client.write(buf);
    askAndRequest();
  });
}

askAndRequest();
