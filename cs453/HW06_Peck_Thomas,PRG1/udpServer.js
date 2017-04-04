var server = require('dgram').createSocket('udp4');

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

server.on('error', (err) => {
  console.log(`server error:\n${err.stack}`);
});

server.on('message', (msg, rinfo) => {
  console.log(`server got: ${msg.toString()} from ${rinfo.address}:${rinfo.port}`);
  if(Math.random() > .5) {
    console.log('Will not process request, we are unreliable');
    return;
  }
  console.log('We will be processing the request');

  try { //if we fail we can just say 300,-1
    var query = msg.toString().split(','); // ex 2,3,+ is 5
    var res = domath(parseInt(query[0]), parseInt(query[1]), query[2]); //do maths

    console.log(`Sending client ${res}`);
    var buf = new Buffer(`${res}`); //build buffer
    server.send(buf, 0, buf.length, 12344, '127.0.0.1');
  } catch (e) {
    //if an error happened anywhere above
    console.log(`failed to process request ${e}`);
    server.send(new Buffer('300,-1'), 0, 6, 12344, '127.0.0.1');
  }
});

server.on('listening', () => {
  var address = server.address();
  console.log(`server listening ${address.address}:${address.port}`);
});

server.bind(12345);
