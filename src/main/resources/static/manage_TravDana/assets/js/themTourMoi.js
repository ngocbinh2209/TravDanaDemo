function themTourMoi() {
    const imageUpload = document.getElementById('imageUpload');
    const imagePreview = document.getElementById('imagePreview');
    var selectedImages = [];
    console.log("ok")
    imageUpload.addEventListener('change', function (event) {
        const files = event.target.files;
        console.log("ok")

        imagePreview.innerHTML = '';

        if (files.length > 0) {
            Array.from(files).forEach((file, index) => {
                if (file.type.startsWith('image/')) {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        const imageContainer = document.createElement('div');
                        imageContainer.classList.add('image-container');
                        imageContainer.dataset.index = index;

                        const img = document.createElement('img');
                        img.src = e.target.result;
                        img.classList.add('thumbnail');

                        const removeButton = document.createElement('button');
                        removeButton.classList.add('btn', 'btn-danger', 'btn-sm', 'remove-btn');
                        removeButton.innerText = 'x';
                        removeButton.addEventListener('click', function () {
                            removeImage(index);
                        });

                        imageContainer.appendChild(img);
                        imageContainer.appendChild(removeButton);
                        imagePreview.appendChild(imageContainer);

                    };
                    reader.readAsDataURL(file);
                    selectedImages.push(file);
                }
            });
        }
    });

    function removeImage(index) {
        selectedImages.splice(index, 1);
        const imageContainer = document.querySelector(`.image-container[data-index="${index}"]`);
        imageContainer.remove();
    }
    var selectedValue = [];
    const displayElement = document.getElementById('site-selected');
    document.getElementById('destination-select').addEventListener('change', function () {

        selectedValue.push(this.value + ' ');
        if (selectedValue) {
            const removeButton = document.createElement('button');
            displayElement.innerHTML = `<p>Điểm dừng đã chọn: ${selectedValue}</p>`;
            removeButton.addEventListener('click', function () {
                removeSite(index);
            });
        } else {
            displayElement.innerHTML = '';
        }
    });
    
}

// hiển thị đường dẫn
function updateBreadcrumb1(section) {
    let currentElement = document.getElementById("current");
    if (currentElement) {
        currentElement.innerText = section;
    } else {
        console.error('Phần tử với id "current" không tồn tại.');
    }
}

function updateBreadcrumb(section) {
    let currentSectionElement = document.getElementById("currentSection");
    if (currentSectionElement) {
        currentSectionElement.innerText = section;
    } else {
        console.error('Phần tử với id "currentSection" không tồn tại.');
    }
}

// hiển thị đường dẫn end

// JS Thông tin tài khoản
let isEditing = false;

function enableEditing() {
    const inputs = document.querySelectorAll('.input-group input, .input-group select');
    inputs.forEach(input => input.disabled = false);
    document.querySelector('.edit-btn').style.display = 'none';
    document.querySelector('.save-btn').style.display = 'inline-block';

    // Bật chế độ chỉnh sửa
    isEditing = true;
}

function saveChanges() {
    const inputs = document.querySelectorAll('.input-group input, .input-group select');
    inputs.forEach(input => input.disabled = true);
    document.querySelector('.edit-btn').style.display = 'inline-block';
    document.querySelector('.save-btn').style.display = 'none';

    // Tắt chế độ chỉnh sửa
    isEditing = false;

    // Code lưu thông tin lên server, nếu cần
    alert('Thông tin đã được lưu!');
}

function triggerFileInput() {
    // Chỉ kích hoạt input file khi đang ở chế độ chỉnh sửa
    if (isEditing) {
        document.getElementById('uploadPicture').click();
    } else {
        alert('Bạn cần nhấn "Chỉnh sửa" để thay đổi ảnh.');
    }
}

function previewImage(event) {
    const profilePicture = document.getElementById('profilePicture');
    const file = event.target.files[0];
    if (file) {
        profilePicture.src = URL.createObjectURL(file);
    }
}
// JS Thông tin tài khoản END

// -------------------------------------------
// Tải IMG lên Đăng kí Tài Khoản
function triggerFileInput() {
    document.getElementById('uploadImg').click();
}

function previewImage(event) {
    const image = document.querySelector('.header_registration img');
    image.src = URL.createObjectURL(event.target.files[0]);
}
// Tải IMG lên Đăng kí Tài Khoản END




