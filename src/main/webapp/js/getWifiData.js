function wifiInfo_click(){
    const table = document.getElementById('indextable');
    const intialtext=document.getElementById('indextext');
    const xmlhttp = new XMLHttpRequest();
    const lat=document.getElementById('lat').value;
    const lnt=document.getElementById('lnt').value;
    const url="http://localhost:8080/wifi?lat="+lat+"&lnt="+lnt;

    // 입력값이 유효한지 검사
    if (isNaN(parseFloat(lat)) || isNaN(parseFloat(lnt))) {
        console.log('잘못된 입력값입니다.');
        return;
    }

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
            const wifiData = JSON.parse(xmlhttp.responseText);
            // 새로운 데이터 추가
            for (let i = 0; i < wifiData.length; i++) {
                const data = wifiData[i];
                const row = table.insertRow(i + 1);
                const distanceCell = row.insertCell(0);
                const mgrNoCell = row.insertCell(1);
                const wrdofcCell = row.insertCell(2);
                const mainNameCell = row.insertCell(3);
                const address1Cell = row.insertCell(4);
                const address2Cell = row.insertCell(5);
                const installFloorCell = row.insertCell(6);
                const installTypeCell = row.insertCell(7);
                const installMbyCell = row.insertCell(8);
                const serviceSeCell = row.insertCell(9);
                const cmcwrCell = row.insertCell(10);
                const cnstcYearCell = row.insertCell(11);
                const inoutDoorCell = row.insertCell(12);
                const remars3Cell = row.insertCell(13);
                const latitudeCell = row.insertCell(14);
                const longitudeCell = row.insertCell(15);
                const workDateCell = row.insertCell(16);

                distanceCell.innerHTML = data.distance_value.toFixed(4);
                mgrNoCell.innerHTML = data.X_SWIFI_MGR_NO;
                wrdofcCell.innerHTML = data.X_SWIFI_WRDOFC;
                mainNameCell.innerHTML = `<a href="detail.jsp?mgrNo=${data.X_SWIFI_MGR_NO}&dist=${data.distance_value.toFixed(4)}">${data.X_SWIFI_MAIN_NM}</a>`;
                address1Cell.innerHTML = data.X_SWIFI_ADRES1;
                address2Cell.innerHTML = data.X_SWIFI_ADRES2;
                installFloorCell.innerHTML = data.X_SWIFI_INSTL_FLOOR;
                installTypeCell.innerHTML = data.X_SWIFI_INSTL_TY;
                installMbyCell.innerHTML = data.X_SWIFI_INSTL_MBY;
                serviceSeCell.innerHTML = data.X_SWIFI_SVC_SE;
                cmcwrCell.innerHTML = data.X_SWIFI_CMCWR;
                cnstcYearCell.innerHTML = data.X_SWIFI_CNSTC_YEAR;
                inoutDoorCell.innerHTML = data.X_SWIFI_INOUT_DOOR;
                remars3Cell.innerHTML = data.X_SWIFI_REMARS3;
                latitudeCell.innerHTML = data.LAT;
                longitudeCell.innerHTML = data.LNT;
                workDateCell.innerHTML = data.WORK_DTTM;
            }
        }
    }
    //History 테이블 업데이트
    createHistory(lat, lnt);
}

