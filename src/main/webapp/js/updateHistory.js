function createHistory(lat, lnt){
    const xmlhttp = new XMLHttpRequest();

    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, "0");
    const day=String(now.getDate()).padStart(2, "0");
    const hours = String(now.getHours()).padStart(2, "0");
    const minutes = String(now.getMinutes()).padStart(2, "0");
    const seconds = String(now.getSeconds()).padStart(2, "0");

    const myDate = `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;

    const url="http://localhost:8080/history?lat="+lat+"&lnt="+lnt+"&time="+myDate;

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        console.log("readyState: " + xmlhttp.readyState + ", status: " + xmlhttp.status);
        console.log(xmlhttp.responseText);
    }
}

// 모든 인코딩 설정을 UTF-8, EUC-KR로 만져봤지만 js 내부에섬 만든 한글이 모두 깨져서 나와서 jsp에서 한글을 받아서 버튼을 만들었다..
// (해결못함)
function loadHistory(del){
    const table = document.getElementById('historytable');
    const intialtext=document.getElementById('historytext');
    const url="http://localhost:8080/loadhistory";
    const xmlhttp = new XMLHttpRequest();

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    if(intialtext.style.display!='none') {
        intialtext.style.display = 'none';
    }

    xmlhttp.onreadystatechange = function() {
        console.log("readyState: " + xmlhttp.readyState + ", status: " + xmlhttp.status);
        console.log(xmlhttp.responseText);
        // 요청이 DONE === 4, DONE이고 정상적으로 처리된 경우 === 200
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            // XMLHttpRequest 객체의 responseText 프로퍼티는 서버에서 받은 JSON 문자열을 담고 있습니다.
            // 이 문자열은 자바스크립트 객체가 아니기 때문에, JSON.parse() 함수를 사용하여 자바스크립트 객체로 변환해줘야 합니다. = parsing
            const historyData = JSON.parse(xmlhttp.responseText);
            // 새로운 데이터 추가
            for (let i = 0; i < historyData.length; i++) {
                const data = historyData[i];
                const row = table.insertRow(i + 1);
                const idCell = row.insertCell(0);
                const latitudeCell = row.insertCell(1);
                const longitudeCell = row.insertCell(2);
                const lookDateCell = row.insertCell(3);
                const beegoCell = row.insertCell(4);
                beegoCell.style.textAlign = 'center';

                idCell.innerHTML = data.HISTORY_ID;
                latitudeCell.innerHTML = data.LOOK_LAT;
                longitudeCell.innerHTML = data.LOOK_LNT;
                lookDateCell.innerHTML = data.LOOK_DATE;
                beegoCell.innerHTML = `<td><input class="del_button" onclick="delHistory(this)" type="button" value=${del}></td>`;
            }
        }
    }
}

function delHistory(button){
    const table = document.getElementById('historytable');
    const row = button.closest('tr');
    const id = row.cells[0].textContent;
    const url="http://localhost:8080/delhistory?id="+id;
    const xmlhttp = new XMLHttpRequest();

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        console.log("readyState: " + xmlhttp.readyState + ", status: " + xmlhttp.status);
        console.log(xmlhttp.responseText);
        // 요청이 DONE === 4, DONE이고 정상적으로 처리된 경우 === 200
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            table.deleteRow(row.rowIndex);
        }
    }
}