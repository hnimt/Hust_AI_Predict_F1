-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th5 26, 2021 lúc 09:50 AM
-- Phiên bản máy phục vụ: 10.4.18-MariaDB
-- Phiên bản PHP: 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `covid19`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `Disease`
--

CREATE TABLE `Disease` (
  `DiseaseID` int(10) UNSIGNED NOT NULL,
  `DiseaseName` text NOT NULL,
  `DiseaseDescription` text NOT NULL,
  `DiseaseImage` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `Disease`
--

INSERT INTO `Disease` (`DiseaseID`, `DiseaseName`, `DiseaseDescription`, `DiseaseImage`) VALUES
(1, 'Covid-19', 'Benh truyen nhiem nguy hiem', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `Evidence`
--

CREATE TABLE `Evidence` (
  `EvidenceID` int(10) UNSIGNED NOT NULL,
  `EvidenceName` text NOT NULL,
  `EvidenceDescription` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `Evidence`
--

INSERT INTO `Evidence` (`EvidenceID`, `EvidenceName`, `EvidenceDescription`) VALUES
(1, 'Khoang cach gan', 'Khoang cach tiep xuc gan giua 2 nguoi'),
(2, 'Khong gian kin', 'Khong hep noi 2 nguoi tiep xuc'),
(3, 'Kha nang mien dich kem', 'Kha nang mien dich kem'),
(4, 'Khong deo khau trang', 'Khong deo khau trang'),
(5, 'Thoi gian tiep xuc dai', 'Thoi gian tiep xuc dai'),
(6, 'Tan xuat tiep xuc nhieu', 'Tan xuat tiep xuc lien tuc');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `EvidenceDisease`
--

CREATE TABLE `EvidenceDisease` (
  `ID` int(10) UNSIGNED NOT NULL,
  `Evidence` int(11) NOT NULL,
  `Disease` int(11) NOT NULL,
  `Positive` float NOT NULL,
  `Neutral` float NOT NULL,
  `Negative` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `EvidenceDisease`
--

INSERT INTO `EvidenceDisease` (`ID`, `Evidence`, `Disease`, `Positive`, `Neutral`, `Negative`) VALUES
(1, 1, 1, 0.8, 0.05, 0.01),
(2, 2, 1, 0.7, 0.1, 0.1),
(3, 3, 1, 0.3, 0.2, 0.1),
(4, 4, 1, 0.9, 0.03, 0.01),
(5, 5, 1, 0.5, 0.2, 0.2),
(6, 6, 1, 0.65, 0.15, 0.15);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `F1`
--

CREATE TABLE `F1` (
  `F1ID` int(10) UNSIGNED NOT NULL,
  `F1Name` text NOT NULL,
  `F1Description` text NOT NULL,
  `F0Name` text NOT NULL,
  `Image` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `F1`
--

INSERT INTO `F1` (`F1ID`, `F1Name`, `F1Description`, `F0Name`, `Image`) VALUES
(1, 'Nguyen Van A', 'Tiep xuc voi Nguyen Van B', 'Tiep xuc voi Nguyen Van B', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `F1Disease`
--

CREATE TABLE `F1Disease` (
  `ID` int(10) UNSIGNED NOT NULL,
  `F1` int(11) NOT NULL,
  `Disease` int(11) NOT NULL,
  `Prediction` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `F1Disease`
--

INSERT INTO `F1Disease` (`ID`, `F1`, `Disease`, `Prediction`) VALUES
(1, 1, 1, 0.67185);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `F1Evidence`
--

CREATE TABLE `F1Evidence` (
  `ID` int(10) UNSIGNED NOT NULL,
  `F1ID` int(11) NOT NULL,
  `Evidence` int(11) NOT NULL,
  `Positive` float NOT NULL,
  `Neutral` float NOT NULL,
  `Negative` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `Disease`
--
ALTER TABLE `Disease`
  ADD PRIMARY KEY (`DiseaseID`);

--
-- Chỉ mục cho bảng `Evidence`
--
ALTER TABLE `Evidence`
  ADD PRIMARY KEY (`EvidenceID`);

--
-- Chỉ mục cho bảng `EvidenceDisease`
--
ALTER TABLE `EvidenceDisease`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `F1`
--
ALTER TABLE `F1`
  ADD PRIMARY KEY (`F1ID`);

--
-- Chỉ mục cho bảng `F1Disease`
--
ALTER TABLE `F1Disease`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `F1Evidence`
--
ALTER TABLE `F1Evidence`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `Disease`
--
ALTER TABLE `Disease`
  MODIFY `DiseaseID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `Evidence`
--
ALTER TABLE `Evidence`
  MODIFY `EvidenceID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `F1`
--
ALTER TABLE `F1`
  MODIFY `F1ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `F1Disease`
--
ALTER TABLE `F1Disease`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `F1Evidence`
--
ALTER TABLE `F1Evidence`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
