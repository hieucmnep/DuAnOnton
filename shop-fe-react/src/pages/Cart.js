import React from "react";
import axios from "axios";
import { Link } from "react-router-dom";

function Cart() {
    const [items, setItems] = React.useState([]);

    function loadData(){
        var username = localStorage.getItem("username");
        axios.get("http://localhost:8080/list-cart-item?username=" + username)
            .then(result => {
                console.log(result);
                setItems(result.data);
            });
    }

    React.useEffect(function () {
        loadData();
    }, [])

    function deleteCartItem(itemId){
        axios.delete("http://localhost:8080/delete-cart-item/" + itemId)
            .then(() => loadData());
    }

    return (
        <div className="container mt-3">
            <table className="table">
                <thead>
                    <tr>
                        <th>Sản phẩm</th>
                        <th>Đơn giá</th>
                        <th>Số lượng</th>
                        <th>Thành tiền</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {items.map(item =>
                        <tr>
                            <td>{item.product.name}</td>
                            <td>{item.product.price}</td>
                            <td>
                                <input type="number" className="form-control"
                                    min="1"
                                    value={item.quantity}
                                    style={{ width: "50px" }} />
                            </td>
                            <td>{item.subTotal}</td>
                            <td>
                                <a className="btn btn-sm btn-danger"
                                    href="#" onClick={() => deleteCartItem(item.id)}>Xóa</a>
                            </td>
                        </tr>
                    )}

                </tbody>
            </table>
            <Link className="btn btn-secondary" to="/">Tiếp tục mua hàng</Link>
            {" "}
            <Link className="btn btn-primary" to="/checkout">Thanh toán</Link>
        </div>
    );
}

export default Cart;