import { useForm } from "react-hook-form";
import { useAuth } from "../../context/AuthContext";
import { useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import Button from "../common/Button";

function RegisterForm() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();
  const { signup, isAuthenticated, errors: registerErrors } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (isAuthenticated) navigate("/");
  }, [isAuthenticated]);

  const onSubmit = handleSubmit(async (values) => {
    await signup(values);
  });

  const handleKeyDown = (data: any) => {
    if (data.key === "Enter") {
      onSubmit();
    }
  };

  return (
    <div className="flex items-center justify-center h-[40rem]">
      <div className="max-w-md w-full p-10 rounded-2xl border border-ctp-mauve  ">
        <h1 className="text-ctp-mauve font-bold">"I exist if I desire it.-"</h1>

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
            type="email"
            className="w-full text-white bg-zinc-800 px-4 py-2 rounded-md my-2"
            placeholder="Email"
            {...register("email", { required: true })}
            name="email"
          />
          {errors.email && <p className="text-ctp-red">Email is required</p>}
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
          {registerErrors && (
            <p className="whitespace-pre-line text-ctp-red">{registerErrors}</p>
          )}

          <div className="flex justify-center py-4">
            <Button text="Register" onClick={onSubmit} />
          </div>
        </form>
        <p className="flex gap-x-2 justify-between text-white">
          Already have an account?{" "}
          <Link to="/login" className="italic text-ctp-lavender">
            Sign in
          </Link>
        </p>
      </div>
    </div>
  );
}

export default RegisterForm;
