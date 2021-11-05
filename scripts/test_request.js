const { exec } = require('child_process');

const testCall = (url, tag) => {
//    console.log(`requested for the ${tag} time:`);
    const startTime = new Date().getTime();


    exec(url, (error, data) => {
            try {
                const _data = JSON.parse(data);
                const endTime = new Date().getTime();
                console.log(`time of ${tag}: ${endTime - startTime}ms ${error + " " + data}`);
            } catch (e) {}
        }
    );
};

const MAX = 20;

    // const url = 'https://onlineup-server.herokuapp.com';
const url = `curl --header "Content-Type: application/json" --request POST --data '{"email":"ntlam19@apcs.vnn"}' http://localhost:8080/test/async`;

console.log(`start call ${url} ${MAX} times`)
for (let i = 0; i < MAX; i++) {
//    setTimeout(()=>{
        testCall(url, i);
//    }, i*1)
}
