import logo from './logo.svg';
import './App.css';
import Home from './pages/Home';
import ProductDetail from './pages/Detail';
import Cart from './pages/Cart';
import Checkout from './pages/Checkout';
import Login from "./pages/Login";

import {  Routes, Route } from "react-router-dom";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />}/>
      <Route path="/product-detail/:id" element={<ProductDetail />}/>
      <Route path="/cart" element={<Cart />}/>
      <Route path="/checkout" element={<Checkout />}/>
      <Route path="/login" element={<Login />}/>
    </Routes>
  );
}

export default App;
