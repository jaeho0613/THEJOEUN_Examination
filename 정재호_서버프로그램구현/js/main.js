// 메뉴 토글 버튼
const toggleBtn = document.querySelector(".navbar__toogleBtn");
const menu = document.querySelector(".navbar__menu");

toggleBtn.addEventListener("click", () => {
  menu.classList.toggle("active");
});

// 토글 메뉴 클릭시 지정 위치로
const navbarMenu = document.querySelector(".navbar__menu");

navbarMenu.addEventListener("click", (event) => {
  const target = event.target;
  const link = target.dataset.link;
  const scrollTo = document.querySelector(link);
  if (link == null) {
    return;
  }
  scrollTo.scrollIntoView({ behavior: "smooth" });
  menu.classList.remove("active");
});
