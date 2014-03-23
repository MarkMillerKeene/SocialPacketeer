-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 25, 2014 at 01:39 AM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `packet`
--

-- --------------------------------------------------------

--
-- Table structure for table `known`
--

CREATE TABLE IF NOT EXISTS `known` (
  `website` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `known`
--

INSERT INTO `known` (`website`, `address`) VALUES
('facebook', '31.13.24'),
('facebook', '31.13.64'),
('facebook', '31.13.71'),
('facebook', '66.220.144'),
('facebook', '69.62.176'),
('facebook', '69.171.224'),
('facebook', '74.119.76'),
('facebook', '103.4.96'),
('facebook', '173.252.64'),
('facebook', '204.15.20'),
('twitter', '199.96.56'),
('twitter', '199.96.57'),
('twitter', '199.16.156'),
('twitter', '199.59.148'),
('twitter', '192.133.76'),
('twitter', '199.96.59'),
('twitter', '199.96.58'),
('twitter', '199.96.63'),
('linkedin', '91.255.250'),
('linkedin', '91.255.249'),
('linkedin', '91.225.248'),
('linkedin', '70.42.142'),
('linkedin', '64.74.98'),
('linkedin', '216.52.242'),
('linkedin', '202.4.184'),
('linkedin', '199.101.162'),
('linkedin', '199.101.162'),
('linkedin', '199.101.160'),
('linkedin', '199.101.160'),
('linkedin', '108.174.3'),
('linkedin', '108.174.3'),
('linkedin', '108.174.2'),
('linkedin', '108.174.2'),
('linkedin', '108.174.15'),
('linkedin', '108.174.15'),
('linkedin', '108.174.0'),
('linkedin', '91.225.249'),
('linkedin', '91.225.248');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
