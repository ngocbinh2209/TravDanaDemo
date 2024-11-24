function toggleContent() {
    const shortContent = document.getElementById("short-content");
    const fullContent = document.getElementById("full-content");
    const toggleBtn = document.getElementById("toggle-btn");

    if (fullContent.style.display === "none") {
        shortContent.style.display = "none";
        fullContent.style.display = "inline";
        toggleBtn.textContent = "Thu gọn";
    } else {
        shortContent.style.display = "inline";
        fullContent.style.display = "none";
        toggleBtn.textContent = "Xem thêm";
    }
}