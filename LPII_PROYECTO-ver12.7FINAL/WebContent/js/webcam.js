// CAMERA SETTINGS.
    Webcam.set({
        width: 640,
        height: 500,
        image_format: 'jpeg',
        jpeg_quality: 100,
    });


    takeSnapshot = function (oButton) {
		 Webcam.attach('#camera');
        document.getElementById('rowid').value = oButton.id;
    }

    addCamPicture = function () {
        var rowid = document.getElementById('rowid').value;
	 	 
        Webcam.snap(function (data_uri) {
            document.getElementById('div_' + rowid).innerHTML = '<img class="rounded" id="foto" src="' + data_uri + '"  width="300px" height="200px" />';
            var base64image = document.getElementById("foto").src;
            document.getElementById('dataurl').value = base64image;
            
            //Genera evento de teclado en el input "dataurl" para que el boostrap validator lo tome como casilla llena.
            var e = document.createEvent("KeyboardEvent"); 
            if (e.initKeyboardEvent) { // Chrome, IE 
                e.initKeyboardEvent("keyup", true, true, document.defaultView, "Enter", 0, "", false, ""); 
            } else { // FireFox 
                e.initKeyEvent("keyup", true, true, document.defaultView, false, false, false, false, 13, 0); 
            } 
            document.getElementById("dataurl").dispatchEvent(e); 
            //--fin
        });

        document.getElementById('rowid').value = ''; 

		Webcam.reset('#camera')
		
  }

	 closeCam = function () {
		Webcam.reset('#camera')
		
  }