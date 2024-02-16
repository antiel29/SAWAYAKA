import { useForm } from "react-hook-form";
import { useAuth } from "../../context/AuthContext";
import { useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import Button from "../common/Button";

function LoginForm() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const { signin, isAuthenticated, errors: loginErrors } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (isAuthenticated) navigate("/");
  }, [isAuthenticated]);

  const onSubmit = handleSubmit(async (data) => {
    signin(data);
  });

  const handleKeyDown = (data: any) => {
    if (data.key === "Enter") {
      onSubmit();
    }
  };

  return (
    <div className="flex items-center justify-center h-[40rem] ">
      <div className="max-w-md w-full p-10 rounded-2xl border border-ctp-lavender ">
        <h1 className=" text-ctp-lavender font-bold">Down the Rabbit-Hole</h1>
        <form onSubmit={onSubmit} onKeyDown={handleKeyDown}>
          <input
            type="text"
            className="w-full text-white bg-zinc-800 px-4 py-2 rounded-md my-2"
            placeholder="Username"
            {...register("username", { required: true })}
            name="username"
          />
          {errors.username && (
            <p className="text-ctp-red">Username is required</p>
          )}
          <input
            className="w-full text-white bg-zinc-800 px-4 py-2 rounded-md my-2"
            placeholder="Password"
            type="password"
            {...register("password", { required: true })}
            name="password"
          />
          {errors.password && (
            <p className="text-ctp-red">Password is required</p>
          )}
          {loginErrors && <p className="text-ctp-red">{loginErrors}</p>}
          <div className="flex justify-center py-4">
            <Button text="Login" onClick={onSubmit} inverseColors />
          </div>
        </form>
        <p className="flex gap-x-2 justify-between text-white">
          New to the forum?{" "}
          <Link to="/register" className=" italic text-ctp-mauve">
            Sign up
          </Link>
        </p>
      </div>
    </div>
  );
}

export default LoginForm;
