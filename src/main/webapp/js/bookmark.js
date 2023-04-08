function addBookmarkGroup(successMsg, failMsg, name, seq){
    const xmlhttp = new XMLHttpRequest();

    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, "0");
    const day=String(now.getDate()).padStart(2, "0");
    const hours = String(now.getHours()).padStart(2, "0");
    const minutes = String(now.getMinutes()).padStart(2, "0");
    const seconds = String(now.getSeconds()).padStart(2, "0");

    const myDate = `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;

    if(typeof name !== 'string' && !Number.isInteger(seq)){
        alert(failMsg);
        location.href="bookmark-group-add.jsp";
        return
    }

    const url="http://localhost:8080/addbookmarkgroup?name="+name+"&seq="+seq+"&date="+myDate;

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        console.log("readyState: " + xmlhttp.readyState + ", status: " + xmlhttp.status);
        console.log(xmlhttp.responseText);
        if (xmlhttp.readyState === 4) {
            if (xmlhttp.status === 200) {
                alert(successMsg);
                location.href="bookmark-group.jsp";
                // 순서가 같은게 있으면 417 반환
            } else if (xmlhttp.status === 417) {
                alert(failMsg);
                location.href="bookmark-group-add.jsp";
            }
        }
    }
}

function loadBookmarkGroup(mod, del){
    const table = document.getElementById('bookmarkgrouptable');
    const intialtext=document.getElementById('bookmarkgrouptext');
    const url="http://localhost:8080/loadbookmarkgroup";
    const xmlhttp = new XMLHttpRequest();

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        console.log("readyState: " + xmlhttp.readyState + ", status: " + xmlhttp.status);
        console.log(xmlhttp.responseText);
        // 요청이 DONE === 4, DONE이고 정상적으로 처리된 경우 === 200
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            // XMLHttpRequest 객체의 responseText 프로퍼티는 서버에서 받은 JSON 문자열을 담고 있습니다.
            // 이 문자열은 자바스크립트 객체가 아니기 때문에, JSON.parse() 함수를 사용하여 자바스크립트 객체로 변환해줘야 합니다. = parsing
            const groupData = JSON.parse(xmlhttp.responseText);
            if (groupData.length!==0){
                if(intialtext.style.display!=='none') {
                    intialtext.style.display = 'none';
                }
            }
            // 새로운 데이터 추가
            for (let i = 0; i < groupData.length; i++) {
                const data = groupData[i];
                const row = table.insertRow(i + 1);
                const idCell = row.insertCell(0);
                const nameCell = row.insertCell(1);
                const seqCell = row.insertCell(2);
                const cDateCell = row.insertCell(3);
                const mDateCell = row.insertCell(4);
                const beegoCell = row.insertCell(5);
                beegoCell.style.textAlign = 'center';

                idCell.innerHTML = data.GROUP_ID;
                nameCell.innerHTML = data.BOOKMARK_NAME;
                seqCell.innerHTML = data.SEQUENCE;
                cDateCell.innerHTML = data.CREATE_DATE;
                if(data.MODIFY_DATE===undefined){
                    mDateCell.innerHTML = '';
                }else{
                    mDateCell.innerHTML = data.MODIFY_DATE;
                }
                beegoCell.innerHTML = `<td><a href="bookmark-group-edit.jsp?id=${data.GROUP_ID}">${mod}</a> <a href="bookmark-group-delete.jsp?id=${data.GROUP_ID}">${del}</a></td>`;
            }
        }
    }
}

function editBookmarkGroup(successMsg, failMsg, id, name, seq){
    const xmlhttp = new XMLHttpRequest();

    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, "0");
    const day=String(now.getDate()).padStart(2, "0");
    const hours = String(now.getHours()).padStart(2, "0");
    const minutes = String(now.getMinutes()).padStart(2, "0");
    const seconds = String(now.getSeconds()).padStart(2, "0");

    const myDate = `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;

    if(typeof name !== 'string' && !Number.isInteger(seq)){
        alert(failMsg);
        location.href=`bookmark-group-edit.jsp?id=${id}&name=${name}&seq=${seq}`;
        return
    }

    const url="http://localhost:8080/editbookmarkgroup?id="+id+"&name="+name+"&seq="+seq+"&date="+myDate;

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        console.log("readyState: " + xmlhttp.readyState + ", status: " + xmlhttp.status);
        console.log(xmlhttp.responseText);
        if (xmlhttp.readyState === 4) {
            if (xmlhttp.status === 200) {
                alert(successMsg);
                location.href="bookmark-group.jsp";
            } else if (xmlhttp.status === 417) {
                alert(failMsg);
                location.href=`bookmark-group-edit.jsp?id=${id}&name=${name}&seq=${seq}`;
            }
        }
    }
}

function delBookmarkGroup(button, id){
    const xmlhttp = new XMLHttpRequest();
    const url="http://localhost:8080/delbookmarkgroup?id="+id;

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        console.log("readyState: " + xmlhttp.readyState + ", status: " + xmlhttp.status);
        console.log(xmlhttp.responseText);
        // 요청이 DONE === 4, DONE이고 정상적으로 처리된 경우 === 200
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            alert(button);
            location.href="bookmark-group.jsp";
        }
    }
}

function loadBookmarkGroupInHistory(){
    const list = document.getElementById('bookmark_group_list');
    const url="http://localhost:8080/loadbookmarkgroup";
    const xmlhttp = new XMLHttpRequest();

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        console.log("readyState: " + xmlhttp.readyState + ", status: " + xmlhttp.status);
        console.log(xmlhttp.responseText);
        // 요청이 DONE === 4, DONE이고 정상적으로 처리된 경우 === 200
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            // XMLHttpRequest 객체의 responseText 프로퍼티는 서버에서 받은 JSON 문자열을 담고 있습니다.
            // 이 문자열은 자바스크립트 객체가 아니기 때문에, JSON.parse() 함수를 사용하여 자바스크립트 객체로 변환해줘야 합니다. = parsing
            const groupData = JSON.parse(xmlhttp.responseText);
            // 새로운 데이터 추가
            for (let i = 0; i < groupData.length; i++) {
                const data = groupData[i];
                var objOption = document.createElement("option");
                objOption.text = data.BOOKMARK_NAME;
                objOption.value = data.GROUP_ID;
                bookmark_group_list.options.add(objOption);
            }
        }
    }
}

function addBookmark(successMsg, wifiId, groupId){
    const xmlhttp = new XMLHttpRequest();

    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, "0");
    const day=String(now.getDate()).padStart(2, "0");
    const hours = String(now.getHours()).padStart(2, "0");
    const minutes = String(now.getMinutes()).padStart(2, "0");
    const seconds = String(now.getSeconds()).padStart(2, "0");

    const myDate = `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;

    const url="http://localhost:8080/addbookmark?wifiId="+wifiId+"&groupId="+groupId+"&date="+myDate;

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        console.log("readyState: " + xmlhttp.readyState + ", status: " + xmlhttp.status);
        console.log(xmlhttp.responseText);
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            alert(successMsg);
            location.href="bookmark-list.jsp";
        }
    }
}

function loadBookmark(del){
    const table = document.getElementById('bookmark_table');
    const intialtext=document.getElementById('bookmarktext');
    const url="http://localhost:8080/loadbookmark";
    const xmlhttp = new XMLHttpRequest();

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        console.log("readyState: " + xmlhttp.readyState + ", status: " + xmlhttp.status);
        console.log(xmlhttp.responseText);
        // 요청이 DONE === 4, DONE이고 정상적으로 처리된 경우 === 200
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            // XMLHttpRequest 객체의 responseText 프로퍼티는 서버에서 받은 JSON 문자열을 담고 있습니다.
            // 이 문자열은 자바스크립트 객체가 아니기 때문에, JSON.parse() 함수를 사용하여 자바스크립트 객체로 변환해줘야 합니다. = parsing
            const bookmarkData = JSON.parse(xmlhttp.responseText);
            if (bookmarkData.length!==0){
                if(intialtext.style.display!=='none') {
                    intialtext.style.display = 'none';
                }
            }
            // 새로운 데이터 추가
            for (let i = 0; i < bookmarkData.length; i++) {
                const data = bookmarkData[i];
                const row = table.insertRow(i + 1);
                const idCell = row.insertCell(0);
                const bookmarkNameCell = row.insertCell(1);
                const wifiNameCell = row.insertCell(2);
                const cDateCell = row.insertCell(3);
                const beegoCell = row.insertCell(4);
                beegoCell.style.textAlign = 'center';

                idCell.innerHTML = data.BOOKMARK_ID;
                bookmarkNameCell.innerHTML = data.BOOKMARK_NAME;
                wifiNameCell.innerHTML = `<a href="../detail.jsp?mgrNo=${data.X_SWIFI_MGR_NO}&dist=${0.0.toFixed(4)}">${data.X_SWIFI_MAIN_NM}</a>`;
                cDateCell.innerHTML = data.CREATE_DATE;
                beegoCell.innerHTML = `<td><a href="bookmark-delete.jsp?id=${data.BOOKMARK_ID}">${del}</a> </td>`;
            }
        }
    }
}

function delBookmark(button, id){
    const xmlhttp = new XMLHttpRequest();
    const url="http://localhost:8080/delbookmark?id="+id;

    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        console.log("readyState: " + xmlhttp.readyState + ", status: " + xmlhttp.status);
        console.log(xmlhttp.responseText);
        // 요청이 DONE === 4, DONE이고 정상적으로 처리된 경우 === 200
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            alert(button);
            location.href="bookmark-list.jsp";
        }
    }
}