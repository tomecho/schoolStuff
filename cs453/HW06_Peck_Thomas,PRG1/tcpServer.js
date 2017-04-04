var net = require('net');

function domath(x,y,oper) {
  //match operater and then do the right math
  if(oper.match(/\+/))
    return `200,${x+y}`;
  else if(oper.match(/\-/))
    return `200,${x-y}`;
  else if(oper.match(/\*/))
    return `200,${x*y}`;
  else if(oper.match(/\//))
    return (y===0 ? "300,-1" : `200,${x/y}`);
  // return 300,-1 if y is zero
  else return "300,-1";
}

var server = net.createServer(function(socket) {
  socket.write(`connected to ${socket.remoteAddress} ${socket.remotePort}`);
  socket.pipe(socket);

  //when we recieve some data
  socket.on('data', function(data) {
    console.log('Received: ' + data);
    try { //if we fail we can just say 300,-1
      var query = data.toString().split(','); // ex 2,3,+ is 5
      var res = domath(parseInt(query[0]), parseInt(query[1]), query[2]); //do maths

      console.log(`Sending client ${res}`);
      var buf = new Buffer(`${res}`); //build buffer
      socket.write(buf);
    } catch (e) {
      //if an error happened anywhere above
      console.log(`failed to process request ${e}`);
      socket.write(new Buffer('300,-1'));
    }
  });

  socket.on('listening', function () {
    console.log('Server started on 12345');
  });

  socket.on('end', function() {
    console.log('Connection closed');
  });

}).listen(12345, function () { console.log("server waiting for requests"); } );
