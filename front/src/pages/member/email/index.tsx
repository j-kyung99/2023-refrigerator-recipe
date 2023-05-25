import { login } from "@/api/login";
import styles from "./styles.module.scss";
import InputContent from "@/components/member/InputContent/InputContent";
import LinkBtn2 from "@/components/member/LinkBtn/LinkBtn2";
import { SetStateAction, useState } from "react";

export default function LoginEmail() {
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");

	const onEmailHandler = (e) => {
		setEmail(e.target.value);
	};

	const onPasswordHandler = (e) => {
		setPassword(e.target.value);
	};

	const handleLogin = () => {
		login(email, password);
	};

	const handleEnterPress = () => {
		handleLogin();
	};

	return (
		<div className={styles.loginEmailContainer}>
			<span className={styles.loginEmailTitle}>로그인</span>
			<InputContent
				type="email"
				title="이메일"
				onChange={onEmailHandler}
				onEnterPress={handleEnterPress}
			/>
			<InputContent
				type="password"
				title="비밀번호"
				onChange={onPasswordHandler}
				onEnterPress={handleEnterPress}
			/>

			<LinkBtn2 title={"로그인"} email={email} password={password} />
		</div>
	);
}
