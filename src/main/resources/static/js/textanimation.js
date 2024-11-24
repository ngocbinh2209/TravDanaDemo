const text_1 = `Đắm mình trong không gian ẩm thực tuyệt vời tại Đà Nẵng, nơi bạn có thể khám
                            phá những hương
                            vị đặc trưng của miền Trung Việt Nam thông qua các món ăn phong phú, từ những món hải sản
                            tươi ngon đến những món đặc sản độc đáo, tạo nên một trải nghiệm ẩm thực đầy màu sắc và đa
                            dạng không thể quên`;
const text_2 = `Hòa mình vào không khí tinh tế và sang trọng của Bà Nà Hills, bạn sẽ được đắm
                            chìm trong
                            không gian ẩm thực đẳng cấp với những nhà hàng sang trọng, thưởng thức những món ăn độc đáo
                            kết hợp cùng với cảnh quan hùng vĩ của dãy núi, tạo nên một trải nghiệm ẩm thực đích thực
                            không thể quên khi ghé thăm Đà Nẵng`;
const text_3 = `Hành trình trải nghiệm ẩm thực của bạn cũng không thể bỏ qua
                            Hội An, nơi mang đến một hòa quyện hài hòa giữa hương vị truyền thống và không gian lãng
                            mạn. Tại Hội An, bạn sẽ được khám phá những hương vị độc đáo của các món ăn địa phương, tạo
                            nên một hành trình ẩm thực đầy thú vị trong chuyến du lịch của mình`;

var index1 = 0;
var index2 = 0;
var index3 = 0;

function textAnimation() {
  setTimeout(function dequy() {
    if (index1 < text_1.length) {
      console.log(text_1.length);
      document.getElementById("ss2-text-1").innerHTML += text_1.charAt(index1);
      index1++;
      setTimeout(dequy, 600);
    } else {
      return;
    }
  }, 0);
  setTimeout(function dequy() {
    if (index1 < text_2.length) {
      console.log(text_2.length);
      document.getElementById("ss2-text-2").innerHTML += text_2.charAt(index2);
      index2++;
      setTimeout(dequy, 600);
    } else {
      return;
    }
  }, 0);
  setTimeout(function dequy() {
    if (index1 < text_3.length) {
      console.log(text_3.length);
      document.getElementById("ss2-text-3").innerHTML += text_3.charAt(index3);
      index3++;
      setTimeout(dequy, 600);
    } else {
      return;
    }
  }, 0);
}

document.addEventListener("DOMContentLoaded", function () {
  window.addEventListener("scroll", function () {
    console.log(this.window.scrollY);
    if (this.window.scrollY >= 1400) {
      textAnimation();
    }
  });
});


