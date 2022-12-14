-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 14, 2022 lúc 03:21 PM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `watches`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `address`
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
-- Cấu trúc bảng cho bảng `brand`
--

CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `brand`
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
-- Cấu trúc bảng cho bảng `cart_item`
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
-- Đang đổ dữ liệu cho bảng `cart_item`
--

INSERT INTO `cart_item` (`id`, `qty`, `size`, `order_id`, `product_id`, `user_id`) VALUES
(4, 3, '4.5', NULL, 1, 1),
(6, 1, '5', NULL, 20, 2),
(7, 1, '4.5', NULL, 19, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `name`, `product_id`) VALUES
(1, 'Đồng hồ thông minh', 1),
(2, 'Đồng hồ thường', 2),
(3, 'Đồng hồ trẻ em', 18),
(4, 'Đồng hồ trẻ em', 19),
(5, 'Đồng hồ trẻ em', 20),
(6, 'Đồng hồ thường', 3),
(7, 'Đồng hồ thường', 6),
(8, 'Đồng hồ thường', 11),
(9, 'Đồng hồ thường', 12),
(10, 'Đồng hồ thường', 17),
(11, 'Đồng hồ thông minh', 4),
(12, 'Đồng hồ thông minh', 7),
(13, 'Đồng hồ thông minh', 8),
(15, 'Đồng hồ thông minh', 9),
(16, 'Đồng hồ thông minh', 10),
(17, 'Đồng hồ thông minh', 13),
(18, 'Đồng hồ thông minh', 14),
(19, 'Đồng hồ thông minh', 15),
(20, 'Đồng hồ thông minh', 16);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(3),
(3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payment`
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
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `description` longtext DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `stock` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `description`, `picture`, `price`, `stock`, `title`) VALUES
(1, 'Đồng hồ nam Casio MTP-1374L-1AVDF chính hãng Casio mang đến cho phái mạnh sự sang trọng, lịch lãm. Đây là sản phẩm tuyệt vời mà các tín đồ của Casio khó có thể bỏ qua.', 'apple-watch', 12000000, 200, 'Casio MTP-1374L-1AVDF'),
(2, 'Phiên bản đồng hồ Samsung Galaxy Watch 4 này được thiết kế đơn giản với mặt đồng hồ dạng tròn có đường kính 40 mm thường thấy ở đồng hồ thông minh tạo nên phong cách năng động, cuốn hút. Khung viền đồng hồ được làm từ chất liệu nhôm bền chắc, khối lượng nhẹ. Bên cạnh đó dây đeo silicone êm ái, không gây khó chịu khi đeo suốt ngày dài, kể cả khi người dùng vận động, tập thể thao ra nhiều mồ hôi tay.', 'galaxy watch 4', 9000000, 50, 'Đồng hồ samsung watch 4'),
(3, 'Shock GD-X6900CM-5DR là phiên bản đồng hồ quân đội đang được yêu thích nhất hiện nay nhờ vào thiết kế mạnh mẽ kết với với những đường nét dứt khoát, đậm chất nam giới. Tuy nhiên, vấn đề về tuổi thọ pin của chiếc đồng hồ này lại luôn là tâm điểm và được nhiều khách hàng đánh giá cao.', 'G - Sock Nam', 4612000, 50, 'G-SHOCK NAM – QUARTZ (PIN)'),
(4, 'Redmi Watch 2 Lite sở hữu thiết kế màn hình lớn với kích thước 1.55 inch, tăng 10% diện tích so với bản tiền nhiệm Mi Watch Lite là 1.4 inch, được trang bị công nghệ màn hình TFT cùng độ phân giải 320 x 360 pixels hiển thị đa dạng màu sắc, hình ảnh rõ ràng, thoả mãn thị giác của bạn. Đồng thời, bạn có thể thay đổi giữa hơn 100 giao diện được thiết kế trong mặt đồng hồ tạo nên sự linh hoạt và đa dạng để phù hợp với trang phục hoặc tâm trạng mỗi ngày của bạn.', 'redmi-watch-2', 1190000, 25, 'Xiaomi Redmi Watch 2 Lite'),
(6, 'Đồng hồ Casio Edifice EFR-526L-2AVUDF chính hãng Casio Nhật Bản với thiết kế cực kỳ ân tượng, mạnh mẽ, sang trọng sẽ cuốn hút bạn ngay từ cái nhìn đầu tiên. Đây là mẫu đồng hồ cao cấp dành cho phái mạnh sử dụng dòng dây da thật mềm mại chắc chắc ôm trọn cổ tay người đeo cùng với mặt đồng hồ được làm từ thép không gỉ mạ bạc sáng bóng mang lại sự đẳng cấp và  sang trọng cho phái mạnh', 'CASIO EFR-526L-2AVUDF ', 3356000, 35, 'CASIO EFR-526L-2AVUDF – NAM '),
(7, 'Là thế hệ kế nhiệm của Band 6, Huawei Band 7 mới có độ dày 9.99 mm và nặng chỉ 16 gram. Thiết bị có màn hình AMOLED 1.47 inch với độ phân giải 194 x 368 pixel. Chiếc đồng hồ cũng được trang bị tính năng Always on Display mà không tiêu tốn nhiều năng lượng.', 'Huawei Band 7', 949000, 100, 'Đồng hồ Huawei Band 7'),
(8, 'Kế thừa tinh hoa của những thế hệ trước, Xiaomi Mi Band 6 là chiếc vòng đeo tay thông minh hoàn hảo khi sở hữu màn hình AMOLED sắc nét, hỗ trợ đo nhịp tim và SpO2, thời lượng pin 2 tuần, hoạt động tập luyện chuyên nghiệp và khả năng chống nước bền bỉ.', 'Mi Band 6', 790000, 200, 'Đồng hồ Xiaomi Mi Band 6'),
(9, 'Apple Watch SE 40mm viền nhôm dây silicone hồng có thiết kế bo tròn 4 góc giúp thao tác vuốt chạm thoải mái hơn. Mặt kính cường lực Ion-X strengthened glass với kích thước 1.57 inch, hiển thị rõ ràng. Khung viền nhôm chắc chắn cùng dây đeo silicone có độ đàn hồi cao, êm ái, tạo cảm giác thoải mái khi đeo.', 'Apple watch SE', 5990000, 150, 'Apple Watch SE 40mm'),
(10, 'Vòng đeo tay thông minh Oppo Band mang đến một diện mạo mới - đầy cá tính và năng động. Dây đeo silicone nhẹ nhàng, không thấm nước, cho bạn thỏa sức hoạt động suốt cả ngày mà không lo tù bí, nặng tay. Màn hình AMOLED 1.1 inch vừa phải, tạo cảm giác thanh lịch khi đeo trên tay nhưng cũng vừa đủ để bạn quan sát thông tin hiển thị một cách dễ dàng.  Mặt khác, chiếc vòng đeo tay còn được trang bị mặt kính 2.5D có độ cứng cao, khó nứt vỡ, sẵn sàng đồng hành cùng bạn trước những hoạt động thường ngày khác.', 'Oppo Band', 790000, 50, 'Đồng hồ Oppo Band'),
(11, 'Với sự trở lại đầy mạnh mẽ của những chiếc đồng hồ điện tử, thương hiệu đồng hồ Casio vừa cho ra mắt phiên bản Casio LA680WGA-9DF với sắc vàng đầy sang trọng, bắt mắt cũng hàng loạt tính năng nổi trội hứa hẹn sẽ rất có ích trong cuộc sống thường ngày của phái mạnh.   ', 'CASIO LA680WGA-9DF', 1892000, 50, 'CASIO LA680WGA-9DF'),
(12, 'Đồng hồ nữ Baby BA-110-7A1DR kiểu dáng mạnh mẽ đầy cá tính, mặt đồng hồ màu nâu đồng, vỏ và dây đeo được làm bằng nhựa cao cấp trắng, với mặt kính chịu lực chống thấm nước ở độ sâu 100m.', 'BABY-G NỮ – QUARTZ (PIN)', 4343000, 100, 'BABY-G NỮ – QUARTZ (PIN)'),
(13, 'Apple Watch SE 44mm viền nhôm dây silicone có màn hình Retina 1.78 inch cùng độ phân giải 448 x 368 pixels giúp hiển thị thông tin và hình ảnh được rõ ràng và sắc nét. Dây đeo silicone có độ đàn hồi cao, tạo cảm giác thoải mái cho cổ tay khi làm việc và tập luyện thể thao. Thiết kế hình vuông bo tròn 4 góc, khá giống với phiên bản Series 5, mặt kính cong 2.5D tạo cảm giác vuốt và chạm mượt mà.', 'Apple Watch SE 44mm (GPS) ', 7950000, 200, 'Apple Watch SE 44mm (GPS) '),
(14, 'Forerunner 255 sở hữu thiết kế đẹp mắt với hai kích thước khác nhau: 255 tiêu chuẩn có vỏ đồng hồ 26 mm và nặng 49g trong khi 255S nhỏ hơn có vỏ đồng hồ 41 mm, dày 12,4 mm và trọng lượng 39g sẽ cho bạn cảm giác nhẹ nhàng trên cổ tay khi hoạt động.  Cũng giống như trên phiên bản cũ, đồng hồ Foreurnner 255S Music sử dụng chất liệu ống kính Corning Gorilla Glass 3 bền bỉ, khung polymer và dây đeo silicone được gia cố bằng sợi và gắn vào vỏ bằng hệ thống Quick-Release tiện lợi.  Đồng hồ có bố cục 5 nút tiêu chuẩn với ba nút ở cạnh trái và hai nút ở bên phải. Điều đáng buồn là không có phiên bản nào của Garmin Forerunner 255 đi kèm màn hình AMOLED, cũng như không có sạc năng lượng mặt trời. Có một sự thay đổi nhỏ đó là chip cảm biến nhịp tim quang học trên thiết bị đeo này đã được bao bọc bởi một lớp kính chứ không phải lớp nhựa nhằm hạn chế chống trầy xước mức tối đa.', 'Garmin Forunner 745', 8490000, 50, 'Garmin Forunner 745'),
(15, 'Đồng hồ thông minh Samsung Galaxy Watch5 LTE 40mm mang đến nhiều tiện ích hiện đại hỗ trợ theo dõi sức khỏe, tập luyện thể thao một cách khoa học. Đồng thời, sản phẩm có độ bền chuẩn quân đội sẵn sàng đồng hành cùng người dùng ở bất kỳ đâu.', 'Galaxy Watch5 40mm LTE', 7490000, 100, 'Galaxy Watch 5 40mm LTE'),
(16, 'Đồng hồ thông minh Samsung Galaxy Watch5 Pro được Samsung giới thiệu tại sự kiện Galaxy Unpacked ra mắt sản phẩm mới diễn ra vào ngày 10/8. Sản phẩm được nâng cấp và tối ưu nhiều tính năng hơn so với tiền nhiệm, hứa hẹn mang đến cho bạn nhiều trải nghiệm vô cùng thú vị.', 'Galaxy Watch5 Pro\r\n', 10250000, 200, 'Samsung Galaxy Watch 5 Pro\r\n'),
(17, 'Là 1 sản phẩm Đồng hồ G-Shock Nhật Bản chuyên chống va đập, chống nước tối thiểu 200m, wr20bar. Đặc biệt BẢO HÀNH 5 NĂM TOÀN QUỐC! Bello là đối tác chính thức của Casio G-Shock tại Việt Nam từ 2009. Mua G-Shock đến Bello!', 'G-SHOCK GA-1000', 8576000, 50, 'G-SHOCK GA-1000'),
(18, 'Đồng hồ định vị trẻ em myAlo KS62w - Thiết bị liên lạc phù hợp với trẻ nhỏ Các bậc phụ huynh muốn trang bị phụ kiện đẹp mắt & có khả năng nghe gọi với bé những lúc cần thiết có thể tìm đến đồng hồ định vị trẻ em myAlo KS62w. Thiết bị này có giao diện thân thiện dễ sử dụng, cho phép phụ huynh giao tiếp hai chiều với bé và luôn mang đến sự bảo vệ an toàn cho bé kịp thời.', 'Đồng hồ trẻ em Myalo KidsPhone ', 2790000, 50, 'Đồng hồ trẻ em Myalo KidsPhone '),
(19, ' myFirst Fone R1 là 1 trong những chiếc đồng hồ định vị cho trẻ em tốt nhất hiện nay. Được sản xuất bởi Oaxis - một thương hiệu công nghệ hướng đến sức khỏe có tiếng tại Singapore, Đồng hồ định vị trẻ em Oaxis Myfirst Fone R1 an toàn cho bé, an tâm cho phụ huynh muốn liên lạc và kiểm soát con mình một cách đơn giản.', 'Đồng hồ định vị trẻ em myFirst Fone R1', 2790000, 100, 'Đồng hồ định vị trẻ em Fone R1'),
(20, 'Đồng hồ định vị GPS Oaxis myFirst Fone R1S là thiết kế dành riêng cho trẻ em với một diện mạo nhỏ xinh cùng sắc màu rực rỡ bên cạnh các tính năng độc đáo và hiện đại như: khả năng định vị chính xác, nghe gọi 2 chiều, video call, đo nhịp tim... nhằm bảo vệ trẻ một cách tối ưu nhất.', 'Đồng hồ định vị trẻ em myFirst Fone R1S', 2990000, 200, 'Đồng hồ định vị trẻ em Fone R1S');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`role_id`, `name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `shipping`
--

CREATE TABLE `shipping` (
  `id` bigint(20) NOT NULL,
  `receiver` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `size`
--

CREATE TABLE `size` (
  `id` bigint(20) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `size`
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
-- Cấu trúc bảng cho bảng `user`
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
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `email`, `first_name`, `last_name`, `password`, `username`, `address_id`) VALUES
(1, 'admin@admin.com', NULL, NULL, '$2a$12$i67EhfS3Hs00vQMOX2/ys.mhue6ycP00vmOA7gApH0aJlfnp/XGDS', 'admin', NULL),
(2, 'thai@gmail.com', 'Thai', 'Phan', '$2a$12$RHbvCpcXkMrVRWtSIS9EbOdo1T33XxIt173qXWmIs6vdoFxKUroxy', 'thaithai', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_order`
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
-- Cấu trúc bảng cho bảng `user_role`
--

CREATE TABLE `user_role` (
  `user_role_id` bigint(20) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user_role`
--

INSERT INTO `user_role` (`user_role_id`, `role_id`, `user_id`) VALUES
(1, 2, 1),
(2, 1, 1),
(3, 1, 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn716qjx07n1pg9eqd712tk7tb` (`product_id`);

--
-- Chỉ mục cho bảng `cart_item`
--
ALTER TABLE `cart_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKen9v41ihsnhcr0i7ivsd7i84c` (`order_id`),
  ADD KEY `FKjcyd5wv4igqnw413rgxbfu4nv` (`product_id`),
  ADD KEY `FKjnaj4sjyqjkr4ivemf9gb25w` (`user_id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqqm689b1x9dotoq6okskaxnx4` (`product_id`);

--
-- Chỉ mục cho bảng `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt7a73xusjdnnsuespcitb683h` (`order_id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`),
  ADD UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`) USING HASH;

--
-- Chỉ mục cho bảng `shipping`
--
ALTER TABLE `shipping`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKj6ayrlwisv2n1yalmjxjlu62p` (`address_id`),
  ADD KEY `FKlh4uncaukk0s3poj5pmv9cm9u` (`order_id`);

--
-- Chỉ mục cho bảng `size`
--
ALTER TABLE `size`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKj2c44yesw2o1kacfugn5oh6sg` (`product_id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKddefmvbrws3hvl5t0hnnsv8ox` (`address_id`);

--
-- Chỉ mục cho bảng `user_order`
--
ALTER TABLE `user_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqjg5jrh5qwnhl2f9lk7n77454` (`payment_id`),
  ADD KEY `FK1hx9xau7q5xwgxjtq63lr7eeg` (`shipping_id`),
  ADD KEY `FKj86u1x7csa8yd68ql2y1ibrou` (`user_id`);

--
-- Chỉ mục cho bảng `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  ADD KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `address`
--
ALTER TABLE `address`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `brand`
--
ALTER TABLE `brand`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT cho bảng `cart_item`
--
ALTER TABLE `cart_item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `payment`
--
ALTER TABLE `payment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
  MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `shipping`
--
ALTER TABLE `shipping`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `size`
--
ALTER TABLE `size`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
