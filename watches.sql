-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 14, 2022 at 01:40 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `watches`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `id` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`id`, `name`, `product_id`) VALUES
(1, 'Casio', 1),
(2, 'Samsung', 2),
(3, 'Xiaomi', 4),
(4, 'Apple', 9),
(5, 'G-Sock', 3),
(6, 'Huawei', 7),
(7, 'Xiaomi', 8),
(8, 'Oppo', 10),
(9, 'Garmin', 14),
(10, 'Casio', 6),
(11, 'Casio', 11),
(12, 'Baby-G', 12),
(13, 'Apple', 13),
(14, 'Samsung', 15),
(15, 'Samsung', 16),
(16, 'G-Sock', 17),
(17, 'myFirst Fone', 18),
(18, 'myFirst Fone', 19),
(19, 'myFirst Fone', 20);

-- --------------------------------------------------------

--
-- Table structure for table `cart_item`
--

CREATE TABLE `cart_item` (
  `id` bigint(20) NOT NULL,
  `qty` int(11) NOT NULL,
  `size` varchar(255) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cart_item`
--

INSERT INTO `cart_item` (`id`, `qty`, `size`, `order_id`, `product_id`, `user_id`) VALUES
(4, 3, '4.5', NULL, 1, 1),
(6, 1, '5', NULL, 20, 2),
(7, 1, '4.5', NULL, 19, 2);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `product_id`) VALUES
(1, 'Đồng hồ thông minh', 1),
(2, 'Đồng hồ thường', 2),
(3, 'Đồng hồ trẻ em', 18),
(4, 'Đồng hồ trẻ em', 19),
(5, 'Đồng hồ trẻ em', 20);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(3),
(3);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL,
  `card_name` varchar(255) DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `cvc` int(11) NOT NULL,
  `expiry_month` int(11) NOT NULL,
  `expiry_year` int(11) NOT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `stock` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `description`, `picture`, `price`, `stock`, `title`) VALUES
(1, '', 'apple-watch', 12000000, 200, 'Casio MTP-1374L-1AVDF'),
(2, '', 'galaxy watch 4', 9000000, 50, 'Đồng hồ samsung watch 4'),
(3, '', 'G - Sock Nam', 4612000, 50, 'G-SHOCK NAM – QUARTZ (PIN)'),
(4, '', 'redmi-watch-2', 1190000, 25, 'Xiaomi Redmi Watch 2 Lite'),
(6, '', 'CASIO EFR-526L-2AVUDF ', 3356000, 35, 'CASIO EFR-526L-2AVUDF – NAM '),
(7, '', 'Huawei Band 7', 949000, 100, 'Đồng hồ Huawei Band 7'),
(8, '', 'Mi Band 6', 790000, 200, 'Đồng hồ Xiaomi Mi Band 6'),
(9, '', 'Apple watch SE', 5990000, 150, 'Apple Watch SE 40mm'),
(10, '', 'Oppo Band', 790000, 50, 'Đồng hồ Oppo Band'),
(11, '', 'CASIO LA680WGA-9DF', 1892000, 50, 'CASIO LA680WGA-9DF'),
(12, '', 'BABY-G NỮ – QUARTZ (PIN)', 4343000, 100, 'BABY-G NỮ – QUARTZ (PIN)'),
(13, '', 'Apple Watch SE 44mm (GPS) ', 7950000, 200, 'Apple Watch SE 44mm (GPS) '),
(14, '', 'Garmin Forunner 745', 8490000, 50, 'Garmin Forunner 745'),
(15, '', 'Galaxy Watch5 40mm LTE', 7490000, 100, 'Galaxy Watch 5 40mm LTE'),
(16, '', 'Galaxy Watch5 Pro\r\n', 10250000, 200, 'Samsung Galaxy Watch 5 Pro\r\n'),
(17, '', 'G-SHOCK GA-1000', 8576000, 50, 'G-SHOCK GA-1000'),
(18, '', 'Đồng hồ trẻ em Myalo KidsPhone ', 2790000, 50, 'Đồng hồ trẻ em Myalo KidsPhone '),
(19, '', 'Đồng hồ định vị trẻ em myFirst Fone R1', 2790000, 100, 'Đồng hồ định vị trẻ em Fone R1'),
(20, '', 'Đồng hồ định vị trẻ em myFirst Fone R1S', 2990000, 200, 'Đồng hồ định vị trẻ em Fone R1S');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `shipping`
--

CREATE TABLE `shipping` (
  `id` bigint(20) NOT NULL,
  `receiver` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `size`
--

CREATE TABLE `size` (
  `id` bigint(20) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `size`
--

INSERT INTO `size` (`id`, `value`, `product_id`) VALUES
(1, '4.5', 1),
(34, '5', 7),
(33, '6', 6),
(32, '5.5', 6),
(31, '5', 6),
(30, '5.5', 4),
(29, '5', 4),
(28, '4.5', 4),
(27, '6.5', 3),
(26, '6', 3),
(25, '5.5', 3),
(24, '5.5', 2),
(23, '5', 2),
(22, '4.5', 2),
(21, '5.5', 1),
(20, '5', 1),
(35, '5.5', 7),
(36, '6', 7),
(37, '4.5', 8),
(38, '5', 8),
(39, '5.5', 8),
(40, '5.5', 9),
(41, '6', 9),
(42, '6.5', 9),
(43, '4.5', 10),
(44, '5', 10),
(45, '5.5', 10),
(46, '5.5', 11),
(47, '6', 11),
(48, '6.5', 11),
(49, '5', 12),
(50, '5.5', 12),
(51, '6', 12),
(52, '5', 13),
(53, '5.5', 13),
(54, '6', 13),
(55, '4.5', 14),
(56, '5', 14),
(57, '5.5', 14),
(58, '5', 15),
(59, '5.5', 15),
(60, '6', 15),
(61, '5.5', 16),
(62, '6', 16),
(63, '6.5', 16),
(64, '5.5', 17),
(65, '6', 17),
(66, '6.5', 17),
(67, '4.5', 18),
(68, '5', 18),
(69, '5.5', 18),
(70, '4.5', 19),
(71, '5', 19),
(72, '5.5', 19),
(73, '4.5', 20),
(74, '5', 20),
(75, '5.5', 20);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `address_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `first_name`, `last_name`, `password`, `username`, `address_id`) VALUES
(1, 'admin@admin.com', NULL, NULL, '$2a$12$i67EhfS3Hs00vQMOX2/ys.mhue6ycP00vmOA7gApH0aJlfnp/XGDS', 'admin', NULL),
(2, 'thai@gmail.com', 'Thai', 'Phan', '$2a$12$RHbvCpcXkMrVRWtSIS9EbOdo1T33XxIt173qXWmIs6vdoFxKUroxy', 'thaithai', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_order`
--

CREATE TABLE `user_order` (
  `id` bigint(20) NOT NULL,
  `order_date` datetime DEFAULT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `order_total` decimal(19,2) DEFAULT NULL,
  `shipping_date` datetime DEFAULT NULL,
  `payment_id` bigint(20) DEFAULT NULL,
  `shipping_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `user_role_id` bigint(20) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_role_id`, `role_id`, `user_id`) VALUES
(1, 2, 1),
(2, 1, 1),
(3, 1, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn716qjx07n1pg9eqd712tk7tb` (`product_id`);

--
-- Indexes for table `cart_item`
--
ALTER TABLE `cart_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKen9v41ihsnhcr0i7ivsd7i84c` (`order_id`),
  ADD KEY `FKjcyd5wv4igqnw413rgxbfu4nv` (`product_id`),
  ADD KEY `FKjnaj4sjyqjkr4ivemf9gb25w` (`user_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqqm689b1x9dotoq6okskaxnx4` (`product_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt7a73xusjdnnsuespcitb683h` (`order_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`),
  ADD UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`) USING HASH;

--
-- Indexes for table `shipping`
--
ALTER TABLE `shipping`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKj6ayrlwisv2n1yalmjxjlu62p` (`address_id`),
  ADD KEY `FKlh4uncaukk0s3poj5pmv9cm9u` (`order_id`);

--
-- Indexes for table `size`
--
ALTER TABLE `size`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKj2c44yesw2o1kacfugn5oh6sg` (`product_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKddefmvbrws3hvl5t0hnnsv8ox` (`address_id`);

--
-- Indexes for table `user_order`
--
ALTER TABLE `user_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqjg5jrh5qwnhl2f9lk7n77454` (`payment_id`),
  ADD KEY `FK1hx9xau7q5xwgxjtq63lr7eeg` (`shipping_id`),
  ADD KEY `FKj86u1x7csa8yd68ql2y1ibrou` (`user_id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  ADD KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `brand`
--
ALTER TABLE `brand`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `cart_item`
--
ALTER TABLE `cart_item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `shipping`
--
ALTER TABLE `shipping`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `size`
--
ALTER TABLE `size`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
