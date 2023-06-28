-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 28, 2023 lúc 02:47 PM
-- Phiên bản máy phục vụ: 10.4.27-MariaDB
-- Phiên bản PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `mydata`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `conga`
--

CREATE TABLE `conga` (
  `congaID` int(11) NOT NULL,
  `congaName` varchar(50) DEFAULT NULL,
  `congaCanNang` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `conga`
--

INSERT INTO `conga` (`congaID`, `congaName`, `congaCanNang`) VALUES
(1, 'Trống', 1.5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pokemon`
--

CREATE TABLE `pokemon` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `pokemon`
--

INSERT INTO `pokemon` (`id`, `name`, `type`) VALUES
(1, 'Squirtle', 'Water'),
(2, 'Pikachu', 'Electric'),
(3, 'Charmander', 'Electric'),
(5, 'Cá rô', 'Nước'),
(7, 'Cá rô', 'Nước'),
(8, 'Cá rô', 'Nước'),
(9, 'Cá rô', 'Nước'),
(10, 'Cá rô', 'Nước');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `product_Id` int(11) NOT NULL,
  `product_Name` varchar(100) DEFAULT NULL,
  `product_Price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `review`
--

CREATE TABLE `review` (
  `id` int(11) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `start` int(11) DEFAULT NULL,
  `pokemon_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `review`
--

INSERT INTO `review` (`id`, `title`, `content`, `start`, `pokemon_id`) VALUES
(1, 'abcxyz', 'qưertyu', 5, 1),
(2, 'trường', 'âsdasdfasd', 5, 2),
(4, 'abcxyz', 'qưertyu', 5, 3),
(5, 'abcxyz', 'qưertyu', 5, 3),
(6, 'abcxyz', 'qưertyu', 5, 3),
(7, 'abcxyz', 'qưertyu', 5, 3),
(8, 'abcxyz', 'qưertyu', 5, 3),
(9, 'abcxyz', 'qưertyu', 5, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `rolename` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`id`, `rolename`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `students`
--

CREATE TABLE `students` (
  `studentId` int(11) NOT NULL,
  `studentName` varchar(100) NOT NULL,
  `studenClass` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `students02`
--

CREATE TABLE `students02` (
  `studenId` int(11) NOT NULL,
  `studentName` varchar(100) DEFAULT NULL,
  `studentEmail` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `students02`
--

INSERT INTO `students02` (`studenId`, `studentName`, `studentEmail`) VALUES
(1, 'Trường', 'nguyentruong272001@gmail.com'),
(2, NULL, NULL),
(3, NULL, NULL),
(4, NULL, NULL),
(5, 'Trường', 'nguyentruong2720011@gmail.com'),
(6, 'Trường', 'nguyentruong2720011@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `userpokemon`
--

CREATE TABLE `userpokemon` (
  `id` int(11) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `userpassword` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `userpokemon`
--

INSERT INTO `userpokemon` (`id`, `username`, `userpassword`) VALUES
(14, 'nguyentruong', '$2a$10$WxLs8uAuO02tE2DyRCPn1O0SAIZ3mmAlBjjLl7Lq53XQnKpohtUwi');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `user_Id` int(11) NOT NULL,
  `user_Name` varchar(100) DEFAULT NULL,
  `user_Password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`user_Id`, `user_Name`, `user_Password`) VALUES
(1, 'nguyentruong', '123456'),
(2, 'nguyenlam', '112233'),
(3, 'nguyennam', '112233'),
(4, 'nguyenlam', '112233'),
(5, 'nguyenvam', '12345');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_roles`
--

CREATE TABLE `user_roles` (
  `user_Role_Id` int(11) NOT NULL,
  `user_Id` int(11) DEFAULT NULL,
  `user_Role_Name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user_roles`
--

INSERT INTO `user_roles` (`user_Role_Id`, `user_Id`, `user_Role_Name`) VALUES
(1, 1, 'Admin'),
(2, 5, 'Sale');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_roless`
--

CREATE TABLE `user_roless` (
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user_roless`
--

INSERT INTO `user_roless` (`user_id`, `role_id`) VALUES
(14, 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `conga`
--
ALTER TABLE `conga`
  ADD PRIMARY KEY (`congaID`);

--
-- Chỉ mục cho bảng `pokemon`
--
ALTER TABLE `pokemon`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_Id`);

--
-- Chỉ mục cho bảng `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_pokemon` (`pokemon_id`);

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`studentId`);

--
-- Chỉ mục cho bảng `students02`
--
ALTER TABLE `students02`
  ADD PRIMARY KEY (`studenId`);

--
-- Chỉ mục cho bảng `userpokemon`
--
ALTER TABLE `userpokemon`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_Id`);

--
-- Chỉ mục cho bảng `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_Role_Id`),
  ADD KEY `FK_User_Roles` (`user_Id`);

--
-- Chỉ mục cho bảng `user_roless`
--
ALTER TABLE `user_roless`
  ADD KEY `user_id` (`user_id`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `conga`
--
ALTER TABLE `conga`
  MODIFY `congaID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `pokemon`
--
ALTER TABLE `pokemon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `product_Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `review`
--
ALTER TABLE `review`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `students02`
--
ALTER TABLE `students02`
  MODIFY `studenId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `userpokemon`
--
ALTER TABLE `userpokemon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `user_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `user_Role_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `fk_pokemon` FOREIGN KEY (`pokemon_id`) REFERENCES `pokemon` (`id`);

--
-- Các ràng buộc cho bảng `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FK_User_Roles` FOREIGN KEY (`user_Id`) REFERENCES `users` (`user_Id`);

--
-- Các ràng buộc cho bảng `user_roless`
--
ALTER TABLE `user_roless`
  ADD CONSTRAINT `user_roless_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `userpokemon` (`id`),
  ADD CONSTRAINT `user_roless_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
