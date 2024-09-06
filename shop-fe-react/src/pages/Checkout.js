import React from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Checkout() {
    const [items, setItems] = React.useState([]);
    const [address, setAddress] = React.useState('');
    const navigate = useNavigate();

    React.useEffect(function () {
        var username = localStorage.getItem("username");
        axios.get("http://localhost:8080/list-cart-item?username=" + username)
            .then(result => {
                console.log(result);
                setItems(result.data);
            });
    }, [])

    function completeOrder() {
        var username = localStorage.getItem("username");
        let body = {
            address: address,
            username: username
        };
        axios.post("http://localhost:8080/complete-order", body)
            .then(() => {
                navigate("/");
            });
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
                    </tr>
                </thead>
                <tbody>
                    {items.map(item =>
                        <tr>
                            <td>{item.product.name}</td>
                            <td>{item.product.price}</td>
                            <td>{item.quantity}</td>
                            <td>{item.subTotal}</td>
                        </tr>
                    )}
                </tbody>
            </table>
            <div>
                <div>
                    <label className="mb-1">Địa chỉ nhận hàng:</label>
                    <textarea className="form-control" rows="2" value={address}
                        onChange={e => setAddress(e.target.value)}></textarea>
                </div>
                <button onClick={completeOrder} className="mt-2 btn btn-primary">Đặt mua</button>
            </div>
        </div>
    )
}

export default Checkout;