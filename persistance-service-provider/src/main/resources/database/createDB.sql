--
-- Database: `cemeterydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `cemeteries`
--

CREATE TABLE IF NOT EXISTS `cemeteries` (
  `cemetery_id` int(3) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `address` varchar(150) NOT NULL,
  PRIMARY KEY(`cemetery_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table containing all the cemeteries in the city';

-- --------------------------------------------------------

--
-- Table structure for table `parcels`
--

CREATE TABLE IF NOT EXISTS `parcels` (
  `parcel_id` int(7) unsigned NOT NULL AUTO_INCREMENT,
  `cemetery_id` int(3) unsigned NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY(`parcel_id`),
  FOREIGN KEY(`cemetery_id`) REFERENCES `cemeteries`(`cemetery_id`),
  UNIQUE KEY `name` (`cemetery_id`, `name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table with all parcels in one cemetery';

-- --------------------------------------------------------

--
-- Table structure for table `structures`
--

CREATE TABLE IF NOT EXISTS `structures` (
  `structure_id` int(7) unsigned NOT NULL AUTO_INCREMENT,
  `parcel_id` int(7) unsigned NOT NULL,
  `name` varchar(50),
  `description` text,
  `type` set('Grave','Monument') NOT NULL COMMENT 'of 2 types : ''Grave'' / ''Monument''',
  `created_on` datetime NOT NULL,
  `width` float NOT NULL,
  `length` float NOT NULL,
  PRIMARY KEY(`structure_id`),
  FOREIGN KEY(`parcel_id`) REFERENCES `parcels`(`parcel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table containing the structure of a parcel (Grave/Monument)';

-- --------------------------------------------------------

--
-- Table structure for table `structurehistory`
--

CREATE TABLE IF NOT EXISTS `structurehistory` (
  `structure_history_id` int(7) unsigned NOT NULL AUTO_INCREMENT,
  `structure_id` int(7) unsigned NOT NULL,
  `description` text NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY(`structure_history_id`),
  FOREIGN KEY(`structure_id`) REFERENCES `structures`(`structure_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table containing history of each parcel';

-- --------------------------------------------------------

--
-- Table structure for table `documents`
--

CREATE TABLE IF NOT EXISTS `documents` (
  `document_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `structure_history_id` int(7) unsigned NOT NULL,
  `document` binary(255) NOT NULL,
  PRIMARY KEY(`document_id`),
  FOREIGN KEY(`structure_history_id`) REFERENCES `structurehistory`(`structure_history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='table containing different types of documents';

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE IF NOT EXISTS `clients` (
  `client_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(60) NOT NULL,
  `cnp` varchar(13) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `home_address` varchar(150) NOT NULL,
  PRIMARY KEY(`client_id`),
  UNIQUE(`cnp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table containing info about all the clients';

-- --------------------------------------------------------

--
-- Table structure for table `restingplacerequests`
--

CREATE TABLE IF NOT EXISTS `restingplacerequests` (
  `request_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `client_id` int(10) unsigned NOT NULL,
  `created_on` datetime NOT NULL,
  `infocet_number` int(10) unsigned NOT NULL,
  `status` varchar(150) NOT NULL,
  PRIMARY KEY(`request_id`),
  FOREIGN KEY(`client_id`) REFERENCES `clients`(`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table containing info regarding requests for resting places';

-- --------------------------------------------------------

--
-- Table structure for table `contracts`
--

CREATE TABLE IF NOT EXISTS `contracts` (
  `contract_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `structure_id` int(7) unsigned NOT NULL,
  `request_id` int(10) unsigned NOT NULL,
  `signed_on` datetime NOT NULL,
  `updated_on` datetime,
  `expires_on` datetime NOT NULL,
  PRIMARY KEY(`contract_id`),
  FOREIGN KEY(`structure_id`) REFERENCES `structures`(`structure_id`),
  FOREIGN KEY(`request_id`) REFERENCES `restingplacerequests`(`request_id`),
  UNIQUE(`request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table containing info about the Contracts (''Ownership''/''Concession'')';

-- --------------------------------------------------------

--
-- Table structure for table `deceased`
--

CREATE TABLE IF NOT EXISTS `deceased` (
  `deceased_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(60) NOT NULL,
  `cnp` varchar(13) NOT NULL,
  `religion` varchar(50) NOT NULL,
  `died_on` datetime NOT NULL,
  PRIMARY KEY(`deceased_id`),
  UNIQUE(`cnp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table containing info about the deceased people';

-- --------------------------------------------------------

--
-- Table structure for table `burialdocuments`
--

CREATE TABLE IF NOT EXISTS `burialdocuments` (
  `burial_document_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `structure_id` int(7) unsigned NOT NULL,
  `deceased_id` int(10) unsigned NOT NULL,
  `buried_on` datetime NOT NULL,
  PRIMARY KEY(`burial_document_id`),
  FOREIGN KEY(`structure_id`) REFERENCES `structures`(`structure_id`),
  FOREIGN KEY(`deceased_id`) REFERENCES `deceased`(`deceased_id`),
  UNIQUE(`deceased_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table containing info in documents of deceased people';

-- --------------------------------------------------------

--
-- Table structure for table `nocaregiverdocuments`
--

CREATE TABLE IF NOT EXISTS `nocaregiverdocuments` (
  `no_caregiver_document_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `deceased_id` int(10) unsigned,
  `certificate_id` int(10) unsigned NOT NULL,
  `request_IML_id` int(10) unsigned NOT NULL,
  PRIMARY KEY(`no_caregiver_document_id`),
  FOREIGN KEY(`deceased_id`) REFERENCES `deceased`(`deceased_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE IF NOT EXISTS `logs` (
  `log_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `table_changed` text NOT NULL,
  `id_affected` varchar(100) NOT NULL,
  `took_place_on` datetime NOT NULL,
  `action` varchar(100) NOT NULL,
  `old_value` text,
  `new_value` text,
  PRIMARY KEY(`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table containing info with all the logs';

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(3) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(150) NOT NULL,
  `token` varchar(50),
  `expires_on` datetime,
  `role` varchar(150) NOT NULL,
  PRIMARY KEY(`user_id`),
  UNIQUE(`username`),
  UNIQUE(`token`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

INSERT INTO users(username, password, role) VALUES('admin', 'admin', 'admin'),('u','p','guest'),('admin2', 'admin2', 'admin');
