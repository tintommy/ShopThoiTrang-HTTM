//// Lấy ra các phần tử cần sử dụng
//const decreaseBtn = document.getElementById("decreaseBtn");
//const increaseBtn = document.getElementById("increaseBtn");
//const inputQuantity = document.getElementById("inputQuantity");
//const conLaiAn = document.getElementById("conLaiAn");
//// Đặt giá trị mặc định là 1
//let quantity = 1;
//let spConLai=conLaiAn.value;
//inputQuantity.value = quantity;
//
//// Thiết lập sự kiện cho các nút
//decreaseBtn.addEventListener("click", function() {
//	if (quantity > 1) {
//		quantity--;
//		inputQuantity.value = quantity;
//	}
//});
//
//increaseBtn.addEventListener("click", function() {
//	if (quantity <spConLai) {
//		quantity++;
//		inputQuantity.value = quantity;
//	}
//});
//
//// Kiểm tra tính hợp lệ của dữ liệu nhập vào
//inputQuantity.addEventListener("change", function() {
//	let value = parseInt(this.value);
//	if (isNaN(value) || value < 1 || value>spConLai) {
//		quantity = 1;
//		this.value = 1;
//	} else {
//		quantity = value;
//	}
//});