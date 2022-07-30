-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 29, 2021 at 05:36 PM
-- Server version: 10.3.25-MariaDB-0ubuntu0.20.04.1
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `farmersportal_kkw`
--

-- --------------------------------------------------------

--
-- Table structure for table `f_category`
--

CREATE TABLE `f_category` (
  `pk_id` int(11) NOT NULL,
  `name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `f_category`
--

INSERT INTO `f_category` (`pk_id`, `name`) VALUES
(1, 'Fruits'),
(2, 'Vegetables'),
(3, 'Grains'),
(4, 'Seeds'),
(5, 'Fertilizers & Pesticide'),
(6, 'Animals'),
(7, 'Farm Machinary'),
(8, 'Transport'),
(9, 'Vehicles'),
(10, 'Weather Forcast'),
(11, 'Market Rate'),
(12, 'Government Schemas');

-- --------------------------------------------------------

--
-- Table structure for table `f_donation`
--

CREATE TABLE `f_donation` (
  `pk_id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `address` varchar(300) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `amount` varchar(200) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `f_donation`
--

INSERT INTO `f_donation` (`pk_id`, `name`, `address`, `phone`, `amount`, `description`, `created_date`) VALUES
(1, '$name', '$address', '$phone', '$amount', '$description', '2021-01-10 08:49:09'),
(2, 'Suyog Jadhav', 'Shramik Nagar, Satpur', '9890015741', '10000', 'NOthing', '2021-01-10 08:51:53'),
(3, 'Suyog Jadhav', 'satpur', '8381069731', '10,000', 'nsjskskkskskksksks', '2021-01-10 08:56:26');

-- --------------------------------------------------------

--
-- Table structure for table `f_feedback`
--

CREATE TABLE `f_feedback` (
  `pk_id` int(11) NOT NULL,
  `description` varchar(500) NOT NULL,
  `rating` varchar(100) NOT NULL,
  `updated_date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `f_feedback`
--

INSERT INTO `f_feedback` (`pk_id`, `description`, `rating`, `updated_date`) VALUES
(1, 'sample first description', '4', '2020-11-07 15:30:13'),
(2, 'ffghfhfh', 'dgdf', '2020-11-12 09:16:07'),
(3, 'rating description', 'rating', '2020-11-12 09:18:26'),
(4, 'jfyfyfyfyg are hy aur cfgcgcgcyctc', '5.0', '2021-01-08 10:53:47'),
(5, 'vuvugihh', '5.0', '2021-01-08 10:55:29');

-- --------------------------------------------------------

--
-- Table structure for table `f_product`
--

CREATE TABLE `f_product` (
  `pk_id` int(11) NOT NULL,
  `product_name` text NOT NULL,
  `photo` text NOT NULL,
  `description` text NOT NULL,
  `category` varchar(200) NOT NULL,
  `seller_name` varchar(300) NOT NULL,
  `seller_address` text NOT NULL,
  `phone` varchar(50) NOT NULL,
  `status` int(3) NOT NULL,
  `price` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `f_product`
--

INSERT INTO `f_product` (`pk_id`, `product_name`, `photo`, `description`, `category`, `seller_name`, `seller_address`, `phone`, `status`, `price`) VALUES
(1, 'Mahindra Tractor', 'https://ayaminteractive.com/farmers_app/Android/Uploads/tractor.png', 'Very Fast and Good COndition tractor .', 'vehicles', 'Santosh Nikam', 'Chandwad Nashik', '9822218636', 1, '50000'),
(2, 'Black Grapes ', 'https://ayaminteractive.com/farmers_app/Android/Uploads/grapes-16955.png', 'Fresh and Healthy Grapes are available in large quanitity', 'fruits', 'Samadhan Shinde', 'Nashik', '8381069732', 1, '50'),
(3, 'Powder Blower', 'https://ayaminteractive.com/farmers_app/Android/Uploads/blower.png', '300 rpms air blower ', 'vehicles', 'Sam Curran', 'Shramik Nagar Satpur', '8381069731', 1, '20000'),
(28, 'Fruit', 'https://ayaminteractive.com/farmers_app/Android/assets/images/websitelogo.png', 'asdgfhgjhjsgfdhg', 'Vegetables', 'dgfhdgjh', 'sdgfhgjhj', '0913 000 5384', 0, '4900'),
(29, 'Mangos', 'https://ayaminteractive.com/farmers_app/Android/assets/images/websitelogo.png', 'asfdgfhh', 'Fruits', 'sdfghjkdfghjklnmdfghjkfghjkfghjkfgh', 'dsgfhghmj', '04567567679', 1, '99.00'),
(30, 'Soyabeen', 'https://ayaminteractive.com/farmers_app/Android/assets/images/rocketicon-1.png', 'Gshshwwgshshhe', 'Grains', 'Anyone', 'sdfghjkfghj', '04567567679', 0, '149.00');

-- --------------------------------------------------------

--
-- Table structure for table `p_enquiry`
--

CREATE TABLE `p_enquiry` (
  `pk_id` int(11) NOT NULL,
  `product_name` varchar(500) NOT NULL,
  `product_price` varchar(500) NOT NULL,
  `seller_name` varchar(500) NOT NULL,
  `name` varchar(500) NOT NULL,
  `phone` varchar(500) NOT NULL,
  `address` varchar(500) NOT NULL,
  `quantity` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `p_enquiry`
--

INSERT INTO `p_enquiry` (`pk_id`, `product_name`, `product_price`, `seller_name`, `name`, `phone`, `address`, `quantity`) VALUES
(1, '$product_name', '$product_price', '$seller_name', '$name', '$phone', '$address', ''),
(2, 'Mahindra Tractor', '50000', 'Santosh Nikam', 'Suyog Jadhav', '8381069741', 'satpur nashik-12', '12'),
(3, 'Mahindra Tractor', '50000', 'Santosh Nikam', 'Suyog Jadhav', '9890015741', 'satpur ', '1'),
(4, 'Mahindra Tractor', '50000', 'Santosh Nikam', 'Suyog Jadhav', 'vhvjuv ch', 'vjvjb', 'I HV vj'),
(5, 'Mahindra Tractor', '50000', 'Santosh Nikam', 'Suyog Jadhav', '9890001545', 'satpur', 'qwryujbv'),
(6, 'Mahindra Tractor', '50000', 'Santosh Nikam', 'uguvjvj', 'jb been jbbkbjbb', 'jvvjvjbbjb', '3'),
(7, 'Mangos', '99.00', 'sdfghjkdfghjklnmdfghjkfghjkfghjkfgh', 'yfuivuvuvu', '75576677677888', 'hvuuvub', '12');

-- --------------------------------------------------------

--
-- Table structure for table `scheme`
--

CREATE TABLE `scheme` (
  `pk_id` int(11) NOT NULL,
  `title` varchar(200) NOT NULL,
  `url` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scheme`
--

INSERT INTO `scheme` (`pk_id`, `title`, `url`) VALUES
(1, 'Information Adarshgaon Yojana and Project', 'http://krishi.maharashtra.gov.in/Site/Upload/Pdf/Website%20Information%20Adarshgaon%20%20Yojana.pdf'),
(2, 'Dr. Babasaheb Ambedkar Krushi Swavalamban Yojna', 'http://krishi.maharashtra.gov.in/Site/Upload/Pdf/Dr_BAKSY.pdf'),
(3, 'Tribal Sub Plan / Birsa Munda Krushi Kranti Yojna', 'http://krishi.maharashtra.gov.in/Site/Upload/Pdf/TSP_scheme.pdf'),
(4, 'Other thaan Tribal Sub Plan / Birsa Munda Krushi Kranti Yojna', 'http://krishi.maharashtra.gov.in/Site/Upload/Pdf/OTSP_scheme.pdf'),
(5, 'Evaluation Report of Kitchen Garden Scheme', 'http://krishi.maharashtra.gov.in/Site/Upload/Pdf/evalution%20report%202018.pdf');

-- --------------------------------------------------------

--
-- Table structure for table `transport`
--

CREATE TABLE `transport` (
  `pk_id` int(11) NOT NULL,
  `name` varchar(300) NOT NULL,
  `rate` varchar(300) NOT NULL,
  `vehicle_no` varchar(500) NOT NULL,
  `phone` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transport`
--

INSERT INTO `transport` (`pk_id`, `name`, `rate`, `vehicle_no`, `phone`) VALUES
(1, 'Shiva Shinde', '50', 'MH 15 DS 2233', ''),
(2, 'Sai Transport', '20', 'MH 23 HG 8899', ''),
(3, '$name', '$rate', '$vehicle_no', '$phone'),
(4, 'Omkar travel', '12', '', '9846494049'),
(5, 'Mahalaxmi Travel', '23', 'MH 67HK 8899', '9464694949');

-- --------------------------------------------------------

--
-- Table structure for table `UploadImageToServer`
--

CREATE TABLE `UploadImageToServer` (
  `id` int(11) NOT NULL,
  `image_path` varchar(500) NOT NULL,
  `image_name` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `UploadImageToServer`
--

INSERT INTO `UploadImageToServer` (`id`, `image_path`, `image_name`) VALUES
(1, 'https://ayaminteractive.com/farmers_app/Android/Uploads/email.png', 'email.png'),
(2, 'https://ayaminteractive.com//farmers_app/Android/Uploads/email.png', 'email.png'),
(3, 'https://ayaminteractive.com/https://ayaminteractive.com/farmers_app/Android/Uploads/davcdsds.png', 'davcdsds'),
(4, 'https://ayaminteractive.com/farmers_app/Android/Uploads/davcdsds.png', 'davcdsds'),
(5, 'https://ayaminteractive.com/https://ayaminteractive.com/farmers_app/Android/Uploads/email.png', 'email.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `f_category`
--
ALTER TABLE `f_category`
  ADD PRIMARY KEY (`pk_id`);

--
-- Indexes for table `f_donation`
--
ALTER TABLE `f_donation`
  ADD PRIMARY KEY (`pk_id`);

--
-- Indexes for table `f_feedback`
--
ALTER TABLE `f_feedback`
  ADD PRIMARY KEY (`pk_id`);

--
-- Indexes for table `f_product`
--
ALTER TABLE `f_product`
  ADD PRIMARY KEY (`pk_id`);

--
-- Indexes for table `p_enquiry`
--
ALTER TABLE `p_enquiry`
  ADD PRIMARY KEY (`pk_id`);

--
-- Indexes for table `scheme`
--
ALTER TABLE `scheme`
  ADD PRIMARY KEY (`pk_id`);

--
-- Indexes for table `transport`
--
ALTER TABLE `transport`
  ADD PRIMARY KEY (`pk_id`);

--
-- Indexes for table `UploadImageToServer`
--
ALTER TABLE `UploadImageToServer`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `f_category`
--
ALTER TABLE `f_category`
  MODIFY `pk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `f_donation`
--
ALTER TABLE `f_donation`
  MODIFY `pk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `f_feedback`
--
ALTER TABLE `f_feedback`
  MODIFY `pk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `f_product`
--
ALTER TABLE `f_product`
  MODIFY `pk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `p_enquiry`
--
ALTER TABLE `p_enquiry`
  MODIFY `pk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `scheme`
--
ALTER TABLE `scheme`
  MODIFY `pk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `transport`
--
ALTER TABLE `transport`
  MODIFY `pk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `UploadImageToServer`
--
ALTER TABLE `UploadImageToServer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
