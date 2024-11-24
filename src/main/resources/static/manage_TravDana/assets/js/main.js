//Thời gian
function time() {
    var today = new Date();
    var weekday = new Array(7);
    weekday[0] = "Chủ Nhật";
    weekday[1] = "Thứ Hai";
    weekday[2] = "Thứ Ba";
    weekday[3] = "Thứ Tư";
    weekday[4] = "Thứ Năm";
    weekday[5] = "Thứ Sáu";
    weekday[6] = "Thứ Bảy";
    var day = weekday[today.getDay()];
    var dd = today.getDate();
    var mm = today.getMonth() + 1;
    var yyyy = today.getFullYear();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    nowTime = h + ":" + m + ":" + s;
    if (dd < 10) {
        dd = '0' + dd
    }
    if (mm < 10) {
        mm = '0' + mm
    }
    today = day + ', ' + dd + '/' + mm + '/' + yyyy;
    tmp = '<i class="fa fa-clock-o" aria-hidden="true"></i> <span class="date">' + today + ' | ' + nowTime +
        '</span>';
    document.getElementById("clock").innerHTML = tmp;
    clocktime = setTimeout("time()", "1000", "Javascript");

    function checkTime(i) {
        if (i < 10) {
            i = "0" + i;
        }
        return i;
    }
}
//Hiển thị content
function loadContent(type) {
    let url = '';
    let bread = ''; // Biến để lưu breadcrumb tương ứng với mỗi section
    let breadcrumbText = ''; // Biến để lưu breadcrumb tương ứng với mỗi section

    switch (type) {
        case 'thongTinCaNhan':
            url = 'fetch/thongTinCaNhan';
            bread = 'Tài khoản'
            breadcrumbText = ' Thông Tin Cá Nhân';
            break;
        case 'doiMatKhau':
            url = 'fetch/doiMatKhau';
            bread = 'Tài khoản'
            breadcrumbText = ' Đổi Mật Khẩu';
            break;
        case 'nhanVien':
            url = 'fetch/qlNhanVien';
            bread = 'Quản lý tài khoản';
            breadcrumbText = 'Quản lý nhân viên';
            break;
        case 'huongDanVien':
            url = 'fetch/qlHuongDanVien';
            bread = 'Quản lý tài khoản'
            breadcrumbText = 'Quản lý hướng dẫn viên';
            break;
        case 'khachHang':
            url = 'fetch/qlKhachHang';
            bread = 'Quản lý tài khoản'
            breadcrumbText = 'Quản lý khách hàng';
            break;
        case 'themTourMoi':
            url = 'fetch/themTourMoi';
            bread = 'Quản lý tour'
            breadcrumbText = 'Thêm Tour Mới';
            break;
        case 'tatCaTour':
            url = 'fetch/tatCaTour';
            bread = 'Quản lý tour'
            breadcrumbText = 'Tất Cả Tour';
            break;
        case 'trangThaiThanhToan':
            url = 'fetch/trangThaiThanhToan';
            bread = 'Quản lý tour'
            breadcrumbText = 'Trạng Thái Thanh Toán Tour';
            break;
        case 'doanhThu':
            url = 'fetch/qlDoanhThu';
            bread = 'Báo cáo thống kê'
            breadcrumbText = 'Quản lý Doanh thu';
            break;
        case 'thongKeTour':
            url = 'fetch/thongKeTour';
            bread = 'Báo cáo thống kê'
            breadcrumbText = 'Thống kê Tour';
            break;
        case 'danhGiaTour':
            url = 'fetch/danhGiaTour';
            bread = 'Quản lý đánh giá'
            breadcrumbText = 'Xem đánh giá tour';
            break;
        case 'xemLichTrinhCongViec':
            url = 'fetch/xemLichTrinhCongViec';
            bread = 'Quản lý lịch trình tour'
            breadcrumbText = 'Xem lịch trình công việc';
            break;
        case 'xemThongTinTour':
            url = 'fetch/xemThongTinTour';
            bread = 'Quản lý lịch trình tour'
            breadcrumbText = 'Xem Thông Tin Tour';
            break;
        default:
            console.warn("Không tìm thấy trang phù hợp.");
            return;
    }

    if (url) {
        // Sử dụng fetch để tải nội dung của file HTML
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Lỗi tải nội dung');
                }
                return response.text();
            })
            .then(data => {
                document.getElementById('content').innerHTML = data;

                // Cập nhật breadcrumb
                updateBreadcrumb1(bread);
                updateBreadcrumb(breadcrumbText);
                handleEmployeeRow();

                // Xóa DataTable cũ nếu đã tồn tại
                if ($.fn.DataTable.isDataTable('#table')) {
                    $('#table').DataTable().destroy();
                }

                // Khởi tạo DataTable
                var table = $('#table').DataTable({
                    buttons: ['copy', 'csv', 'print', 'excel', 'pdf', 'colvis'],
                    dom: "<'row'<'col-md-3'l><'col-md-5'B><'col-md-4'f>>" +
                        "<'row'<'col-md-12'tr>>" +
                        "<'row'<'col-md-5'i><'col-md-7'p>>",
                    lengthMenu: [
                        [5, 10, 25, 50, 100, -1],
                        [5, 10, 25, 50, 100, "All"]
                    ]
                });

                // Đặt các nút vào container thay vì sử dụng .content()
                table.buttons().container()
                    .appendTo('#table_wrapper .col-md-5:eq(0)');

                // Gọi hàm themTourMoi() nếu cần
                themTourMoi();

            })
            .catch(error => {
                console.error('Lỗi:', error);
            });
    }
}



function handleEmployeeRow() {employee_form
    const addButton = document.querySelector('.content-btn.btn-more');
    const saveButton = document.querySelector('#saveEmployeeBtn');
    const employee_form = document.querySelector('#employee_form');
    const addEmployeeRow = document.querySelector('#addEmployeeRow');
    const genderSelect = document.querySelector('#gender');

    if (addButton) {
        addButton.addEventListener('click', function() {
            addEmployeeRow.style.display = '';
            if (genderSelect) genderSelect.disabled = false;
        });
    }

    if (saveButton) {
        saveButton.addEventListener('click', function() {
            addEmployeeRow.style.display = 'none';
            if (genderSelect) genderSelect.disabled = true;
        });
    }
}

document.addEventListener("DOMContentLoaded", handleEmployeeRow);



// sideBarRight
var sideBarRight = document.querySelector('.sideBarRight');
var bellIcon = document.getElementById('bell-icon'); // Icon chuông
var content = document.getElementById('content');

// siderBar - mobile-menu
var slideBar = document.getElementById('siderBar');
var mobileMenu = document.getElementById('mobile-menu');
var menuItems = slideBar.querySelectorAll('li'); // Lấy tất cả thẻ <li> bên trong slideBar

// Đặt vị trí ban đầu cho slideBar là -250px (bên ngoài màn hình)
slideBar.style.left = '-250px';

// Khi click vào biểu tượng chuông (mở sideBarRight)
bellIcon.onclick = function (e) {
    e.stopPropagation(); // Ngăn sự kiện này ảnh hưởng tới các click ngoài khác

    // Nếu slideBar đang mở thì đóng nó
    if (slideBar.style.left === '0px') {
        slideBar.style.left = '-250px';
    }

    // Toggle trạng thái sideBarRight
    if (sideBarRight.style.display === 'block') {
        sideBarRight.style.display = 'none'; // Ẩn sideBarRight nếu đang mở
    } else {
        sideBarRight.style.display = 'block'; // Hiện sideBarRight nếu đang ẩn
    }
};

// Khi click bất kỳ đâu trên trang ngoại trừ sideBarRight và biểu tượng chuông
document.addEventListener('click', function (e) {
    if (!sideBarRight.contains(e.target) && !bellIcon.contains(e.target)) {
        sideBarRight.style.display = 'none'; // Ẩn sideBarRight nếu click ngoài
    }
});

// Khi click vào mobile menu (mở siderBar)
mobileMenu.onclick = function (e) {
    e.stopPropagation(); // Ngăn sự kiện click lan ra ngoài

    // Nếu sideBarRight đang mở, đóng nó
    if (sideBarRight.style.display === 'block') {
        sideBarRight.style.display = 'none';
    }

    // Toggle trạng thái của slideBar
    if (slideBar.style.left === '-250px' || slideBar.style.left === '') {
        slideBar.style.left = '0'; // Mở sidebar

        // Thêm sự kiện click ngoài slideBar để đóng sidebar
        document.addEventListener('click', handleOutsideClick);
    } else {
        slideBar.style.left = '-250px'; // Đóng sidebar

        // Xóa sự kiện click ngoài slideBar khi sidebar đóng
        document.removeEventListener('click', handleOutsideClick);
    }
};

// Thêm sự kiện click vào từng <li> bên trong slideBar
menuItems.forEach(function (item, index) {
    item.addEventListener('click', function () {
        // Nếu không phải là thẻ <li> thứ 4 (index === 3), thì đóng slideBar
        if (index !== 3) { // Ở đây index là 0-based, nên index = 3 là thẻ <li> thứ 4
            slideBar.style.left = '-250px'; // Đóng sidebar khi nhấp vào một thẻ <li>
            document.removeEventListener('click', handleOutsideClick); // Gỡ sự kiện click bên ngoài
        }
    });
});

// Hàm xử lý khi click ra ngoài slideBar
function handleOutsideClick(e) {
    // Nếu nhấp chuột không nằm trong slideBar và mobileMenu
    if (!slideBar.contains(e.target) && !mobileMenu.contains(e.target)) {
        slideBar.style.left = '-250px'; // Đóng sidebar
        document.removeEventListener('click', handleOutsideClick); // Gỡ sự kiện để tránh thực hiện nhiều lần
    }
}

// -----------------------------------------------------
// backTop

$(document).ready(function () {
    $(window).scroll(function () {
        if ($(this).scrollTop()) {
            $('#backTop').fadeIn();
        } else {
            $('#backTop').fadeOut();
        }
    });
    $("#backTop").click(function () {
        $('html, body').animate({
            scrollTop: 0
        }, 300);
    });
});


// Lấy tất cả các mục trong siderbar
const navItems = document.querySelectorAll('.nav-item');

navItems.forEach(item => {
    item.addEventListener('click', function () {
        // Xóa lớp "active" khỏi tất cả các mục
        navItems.forEach(nav => nav.classList.remove('active'));

        // Thêm lớp "active" vào mục được nhấp
        this.classList.add('active');
    });
});