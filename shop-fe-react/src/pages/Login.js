import React from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./Login.css";

function Login() {
    const navigate = useNavigate();
    const [username, setUsername] = React.useState("");
    const [password, setPassword] = React.useState("");

    function login() {
        var body = {
            username: username,
            password: password
        };
        axios.post("http://localhost:8080/login", body)
            .then(() => {
                localStorage.setItem("username", username);
                navigate("/");
            })
            .catch(() => alert("Tên đăng nhập/mật khẩu không đúng"));
    }
    return (
        <div className="bg-login">
            <div className="login-form">
                <h3>Đăng nhập</h3>

                <form method="POST" style={{ marginTop: "30px" }}>
                    <div className="mt-3">
                        <label className="mb-1">Tên tài khoản</label>
                        <input value={username} onChange={e => setUsername(e.target.value)}
                            type="text" className="form-control" />
                    </div>
                    <div className="mt-4">
                        <label htmlFor="password" className="mb-1">Mật khẩu</label>
                        <input value={password} onChange={e => setPassword(e.target.value)}
                            type="password" className="form-control" />
                    </div>
                    <div className="mt-3">
                        <span id="error" style={{ color: "red" }}></span>
                    </div>
                    <div style={{ marginTop: "35px" }}>
                        <button onClick={login} type="button" className="btn btn-primary" style={{ width: "100%" }}>Đăng nhập</button>
                    </div>
                </form>
                <p className="text-center mt-4"><a href="#/">Đăng ký tài khoản</a></p>
            </div>
        </div>
    );
}
export default Login;