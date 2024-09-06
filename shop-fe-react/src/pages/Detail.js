import { Link, useNavigate, useParams } from "react-router-dom";
import React from "react";
import axios from "axios";

function ProductDetail() {
    const { id } = useParams();
    const navigate = useNavigate();

    const [product, setProduct] = React.useState({});
    React.useEffect(function () {
        axios.get("http://localhost:8080/product-detail/" + id)
            .then(result => {
                console.log(result);
                setProduct(result.data);
            });
    }, [])

    function addCartItem() {
        let username = localStorage.getItem("username");
        if (username == null) {
            navigate("/login");
        } else {
            var body = {
                productId: id,
                quantity: 1,
                username: username
            };
            console.log("body:", body);
            axios.post("http://localhost:8080/add-cart-item", body)
                .then(() => navigate("/cart"))
                .catch(e => alert("Có lỗi xảy ra"));
        }
    }

    return (
        <div className="container mt-5 mb-5" ng-app="myApp" ng-controller="myCtrl">
            <div className="row">
                <div className="col-6">
                    <img alt="" className="product-detail-image"
                        src={product.image} />
                </div>

                <div className="col-6 mt-5">
                    <div className="product-detail-title">{product.name}</div>
                    <br />
                    <table className="table">
                        <tbody>
                            <tr>
                                <td>Loại điện thoại:</td>
                                <td>{product.category != null ? product.category.name : ''}</td>
                            </tr>
                            <tr>
                                <td>Giá bán:</td>
                                <td><b>{product.price} ₫</b></td>
                            </tr>
                        </tbody>
                    </table>
                    <br />
                    <Link className="btn btn-secondary" to="/">Quay lại</Link>
                    {" "}
                    <button onClick={addCartItem} className="btn btn-primary">Mua hàng</button>
                </div>
            </div>
        </div>
    );
}
export default ProductDetail;