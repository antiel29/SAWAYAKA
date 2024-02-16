function Button({ text, onClick, inverseColors }: any) {
  const buttonClassName = inverseColors
    ? "w-60 h-10 text-ctp-base font-bold bg-ctp-lavender rounded-2xl hover:bg-ctp-mauve active:bg-ctp-mauve/75"
    : "w-60 h-10 text-ctp-base font-bold bg-ctp-mauve rounded-2xl hover:bg-ctp-lavender active:bg-ctp-lavender/75";

  return (
    <button className={buttonClassName} type="button" onClick={onClick}>
      {text}
    </button>
  );
}

export default Button;
