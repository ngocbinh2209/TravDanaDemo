const submit_employee = () => {
    const employee_form = document.getElementById("employee_form");
    const formData = new FormData(employee_form)
    const data = {}
    for (let [key, value] of formData.entries()){
        data[key] = value;
    }

     fetch("fetch/qlNhanVien2",{
        method : "POST",
         headers : {
             "Content-Type": "application/json"
         },
        body: JSON.stringify(data)
        },

    ).then(response => {
         if (!response.ok) {
             throw new Error('Lỗi tải nội dung');
         }
         return response.text()}
     ) .then(data => {
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

