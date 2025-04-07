-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 07, 2025 at 06:00 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `orcatudo`
--

-- --------------------------------------------------------

--
-- Table structure for table `carrinho`
--

CREATE TABLE `carrinho` (
  `id_carrinho` int(11) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `categoria`
--

CREATE TABLE `categoria` (
  `id_categoria` int(11) NOT NULL,
  `nome` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `fornecedor`
--

CREATE TABLE `fornecedor` (
  `id_fornecedor` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `razao_social` varchar(100) DEFAULT NULL,
  `email` varchar(200) NOT NULL,
  `telefone` varchar(255) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `area_de_atuacao` varchar(45) DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `avaliacao` int(11) NOT NULL,
  `senha` varchar(200) DEFAULT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `item_carrinho`
--

CREATE TABLE `item_carrinho` (
  `id_itemCarrinho` int(11) NOT NULL,
  `id_produto` int(11) DEFAULT NULL,
  `quantidade` int(11) NOT NULL,
  `valor_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `licitacao`
--

CREATE TABLE `licitacao` (
  `id_licitacao` int(11) NOT NULL,
  `data_inicio` datetime NOT NULL,
  `data_fim` datetime NOT NULL,
  `valor_final` double NOT NULL,
  `status` tinyint(1) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `id_fornecedor` int(11) DEFAULT NULL,
  `id_produto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `operacao`
--

CREATE TABLE `operacao` (
  `id_operacao` int(11) NOT NULL,
  `tipo_operacao` varchar(20) NOT NULL,
  `data` date NOT NULL,
  `id_pedido` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` int(11) NOT NULL,
  `data` date NOT NULL,
  `valor_total` double NOT NULL,
  `status` tinyint(1) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `id_carrinho` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `produto`
--

CREATE TABLE `produto` (
  `id_produto` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `descricao` text DEFAULT NULL,
  `preco` double NOT NULL,
  `imagem` mediumblob DEFAULT NULL,
  `id_subcategoriaTernaria` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `name` enum('FORNECEDOR','USUARIO') DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `created_at`, `description`, `name`, `updated_at`) VALUES
(2, '2025-04-04 00:36:04.000000', 'role de Usuario/Cliente', 'USUARIO', '2025-04-04 00:36:04.000000'),
(3, '2025-04-04 00:36:04.000000', 'Role de fornecedor', 'FORNECEDOR', '2025-04-04 00:36:04.000000');

-- --------------------------------------------------------

--
-- Table structure for table `roles_seq`
--

CREATE TABLE `roles_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles_seq`
--

INSERT INTO `roles_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Table structure for table `subcategoria`
--

CREATE TABLE `subcategoria` (
  `id_subcategoria` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `id_categoria` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `subcategoria_ternaria`
--

CREATE TABLE `subcategoria_ternaria` (
  `id_subcategoriaTernaria` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `id_subcategoria` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `cpf_cnpj` varchar(14) NOT NULL,
  `telefone` int(11) DEFAULT NULL,
  `endereco` varchar(250) DEFAULT NULL,
  `senha` varchar(200) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `email`, `cpf_cnpj`, `telefone`, `endereco`, `senha`, `nome`, `role_id`) VALUES
(5, 'ronyelson@gmail.com', '1223434312', NULL, NULL, '$2a$10$BJoXqH0KtddDrlcQ.otvVuVBfsVHZmH.LodpDQkgHAbxvpTQ84wXO', 'rony gol da silva', 2),
(7, 'ronyson@gmail.com', '132434312', NULL, NULL, '$2a$10$QSe7/Y6m0VT.Nf/e1hiYjOS276lViT0yXzDIVVjRd20S7nW2PKYZO', 'rony gol silva', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carrinho`
--
ALTER TABLE `carrinho`
  ADD PRIMARY KEY (`id_carrinho`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indexes for table `fornecedor`
--
ALTER TABLE `fornecedor`
  ADD PRIMARY KEY (`id_fornecedor`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `FKpu45ygbqj51yt1i16jk1jffsy` (`role_id`);

--
-- Indexes for table `item_carrinho`
--
ALTER TABLE `item_carrinho`
  ADD PRIMARY KEY (`id_itemCarrinho`),
  ADD KEY `id_produto` (`id_produto`);

--
-- Indexes for table `licitacao`
--
ALTER TABLE `licitacao`
  ADD PRIMARY KEY (`id_licitacao`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_fornecedor` (`id_fornecedor`),
  ADD KEY `id_produto` (`id_produto`);

--
-- Indexes for table `operacao`
--
ALTER TABLE `operacao`
  ADD PRIMARY KEY (`id_operacao`),
  ADD KEY `id_pedido` (`id_pedido`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indexes for table `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_carrinho` (`id_carrinho`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`id_produto`),
  ADD KEY `id_subcategoriaTernaria` (`id_subcategoriaTernaria`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKofx66keruapi6vyqpv6f2or37` (`name`);

--
-- Indexes for table `subcategoria`
--
ALTER TABLE `subcategoria`
  ADD PRIMARY KEY (`id_subcategoria`),
  ADD KEY `id_categoria` (`id_categoria`);

--
-- Indexes for table `subcategoria_ternaria`
--
ALTER TABLE `subcategoria_ternaria`
  ADD PRIMARY KEY (`id_subcategoriaTernaria`),
  ADD KEY `id_subcategoria` (`id_subcategoria`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `cpf_cnpj` (`cpf_cnpj`),
  ADD KEY `FKeljjw3mx8n5ngoe7fbqbjwusp` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `carrinho`
--
ALTER TABLE `carrinho`
  MODIFY `id_carrinho` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `fornecedor`
--
ALTER TABLE `fornecedor`
  MODIFY `id_fornecedor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `item_carrinho`
--
ALTER TABLE `item_carrinho`
  MODIFY `id_itemCarrinho` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `licitacao`
--
ALTER TABLE `licitacao`
  MODIFY `id_licitacao` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `operacao`
--
ALTER TABLE `operacao`
  MODIFY `id_operacao` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `id_produto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `subcategoria`
--
ALTER TABLE `subcategoria`
  MODIFY `id_subcategoria` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `subcategoria_ternaria`
--
ALTER TABLE `subcategoria_ternaria`
  MODIFY `id_subcategoriaTernaria` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `carrinho`
--
ALTER TABLE `carrinho`
  ADD CONSTRAINT `carrinho_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`);

--
-- Constraints for table `fornecedor`
--
ALTER TABLE `fornecedor`
  ADD CONSTRAINT `FKpu45ygbqj51yt1i16jk1jffsy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);

--
-- Constraints for table `item_carrinho`
--
ALTER TABLE `item_carrinho`
  ADD CONSTRAINT `item_carrinho_ibfk_1` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`);

--
-- Constraints for table `licitacao`
--
ALTER TABLE `licitacao`
  ADD CONSTRAINT `licitacao_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `licitacao_ibfk_2` FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedor` (`id_fornecedor`),
  ADD CONSTRAINT `licitacao_ibfk_3` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`);

--
-- Constraints for table `operacao`
--
ALTER TABLE `operacao`
  ADD CONSTRAINT `operacao_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`),
  ADD CONSTRAINT `operacao_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`);

--
-- Constraints for table `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`id_carrinho`) REFERENCES `carrinho` (`id_carrinho`);

--
-- Constraints for table `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `produto_ibfk_1` FOREIGN KEY (`id_subcategoriaTernaria`) REFERENCES `subcategoria_ternaria` (`id_subcategoriaTernaria`);

--
-- Constraints for table `subcategoria`
--
ALTER TABLE `subcategoria`
  ADD CONSTRAINT `subcategoria_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id_categoria`);

--
-- Constraints for table `subcategoria_ternaria`
--
ALTER TABLE `subcategoria_ternaria`
  ADD CONSTRAINT `subcategoria_ternaria_ibfk_1` FOREIGN KEY (`id_subcategoria`) REFERENCES `subcategoria` (`id_subcategoria`);

--
-- Constraints for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `FKeljjw3mx8n5ngoe7fbqbjwusp` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
