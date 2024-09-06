import React from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';


function Home() {
    const [items, setItems] = React.useState([]);
    const [keyword, setKeyword] = React.useState('');
    const [categoryId, setCategoryId] = React.useState('');
    function search() {
        var url = "http://localhost:8080/search-product?keyword=" + keyword;
        if (categoryId != '') {
            url += "&categoryId=" + categoryId;
        }
        axios.get(url)
            .then(result => {
                console.log(result.data);
                setItems(result.data);
            })
    }
    React.useEffect(function () {
        search();
    }, []);

    return (
        <div className="container mt-5 mb-5" ng-app="myApp" ng-controller="myCtrl">
            <div className="row">
                <div className="col-3 p-3 card">
                    <form>
                        <div className="product-search-info mt-3">
                            <label htmlFor="name" className="mb-1"><b>Tên sản phẩm:</b></label>
                            <input className="form-control"
                                placeholder="Nhập tên sản phẩm để tìm"
                                value={keyword}
                                onChange={e => setKeyword(e.target.value)} />
                        </div>

                        <div className="brand-search-info mt-3">
                            <label htmlFor="brandId"><b>Loại điện thoại:</b></label>
                            <div className="mt-2">
                                <input type="radio" value=""
                                    checked={categoryId == ''}
                                    onChange={e => setCategoryId('')}
                                />
                                <span>Tất cả</span>
                            </div>
                            <div className="mt-1">
                                <input type="radio" value=""
                                    checked={categoryId == 'IP'}
                                    onChange={e => setCategoryId('IP')}
                                />
                                <span>IPhone</span>
                            </div>
                            <div className="mt-1">
                                <input type="radio" value=""
                                    checked={categoryId == 'ANDR'}
                                    onChange={e => setCategoryId('ANDR')}
                                />
                                <span>Android</span>
                            </div>
                        </div>

                        <div className="price-search-info mt-3">
                            <label htmlFor="priceRange"><b>Mức giá:</b></label>
                            <div className="mt-2">
                                <input type="radio" name="priceRange" checked value="" />
                                <span>Tất cả</span>
                            </div>

                            <div className="mt-1">
                                <input type="radio" name="priceRange" value="1" />
                                <span>Dưới 10 triệu</span>
                            </div>

                            <div className="mt-1">
                                <input type="radio" name="priceRange" value="2" />
                                <span>Từ 10-20 triệu</span>
                            </div>

                            <div className="mt-1">
                                <input type="radio" name="priceRange" value="3" />
                                <span>Trên 20 triệu</span>
                            </div>
                        </div>

                        <button type="button" onClick={search} className="btn btn-primary mt-4 mb-4">Tìm kiếm</button>
                    </form>
                </div>
                <div className="col-9">
                    <ul className="list-unstyled row">
                        {items.map(item =>
                            <li className="list-item col-sm-4 mt-3">
                                <div className="item-container">
                                    <Link to={"/product-detail/" + item.id} className="product-item">
                                        <img src={item.image}
                                            className="product-image" alt="" />
                                        <div className="item-info">
                                            <div>
                                                <span className="product-name">{item.name}</span>
                                            </div>
                                            <div>
                                                <span className="price-title">Giá bán :</span>
                                                <span className="price">{item.price} ₫</span>
                                            </div>
                                        </div>
                                    </Link>
                                </div>
                            </li>
                        )}
                    </ul>
                    <nav aria-label="Page navigation example">
                        <ul className="pagination">
                            <li className="page-item"><a className="page-link" href="#/">&laquo;</a></li>
                            <li className="page-item"><a className="page-link" href="#/">&lsaquo;</a></li>
                            <li className="page-item"><a className="page-link" href="#/">&rsaquo;</a></li>
                            <li className="page-item"><a className="page-link" href="#/">&raquo;</a></li>
                        </ul>
                        <span>Hiển thị 1-10 trên tổng số 25 sản phẩm</span>
                    </nav>
                </div>
            </div>
        </div>
    )
}

export default Home;