console.log("Admin User");

document.querySelector("#image_file_input").addEventListener('change',function(event) {
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.onload = function(){
        document.querySelector("#upload_img_prev").setAttribute("src",reader.result);
    };
    reader.readAsDataURL(file);
})