function location_click() {
    const lat = document.getElementById('lat');
    const lnt = document.getElementById('lnt');

    navigator.geolocation.getCurrentPosition(function(pos) {
        const latitude = pos.coords.latitude;
        const longitude = pos.coords.longitude;
        lat.value = latitude;
        lnt.value = longitude;
    });
}

