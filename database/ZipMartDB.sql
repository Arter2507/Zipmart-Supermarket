-- Enable the NOCOUNT option to prevent the return of the count of affected rows by DML statements.
SET NOCOUNT ON;
GO
GO
-- Use the 'master' database to perform system management operations.
USE [master];
GO
GO
-- Check IF the 'Cp2396g01_group5_db' database exists, and drop it IF it does.
IF EXISTS (SELECT * FROM sys.databases WHERE name='Cp2396g01_group5_db')
BEGIN
    DROP DATABASE [Cp2396g01_group5_db];
END
GO
GO
-- Get the directory path of the master data file to use for the new 'Cp2396g01_group5_db' data and log files.
DECLARE @device_directory NVARCHAR(520);
SELECT @device_directory = SUBSTRING(physical_name, 1, CHARINDEX(N'master.mdf', LOWER(physical_name)) - 1)
FROM master.sys.master_files WHERE database_id = 1 AND file_id = 1;

-- Create the 'Cp2396g01_group5_db' database with primary data file and log file locations.
EXECUTE ('CREATE DATABASE Cp2396g01_group5_db
  ON PRIMARY (NAME = N''Cp2396g01_group5_db_v1'', FILENAME = N''' + @device_directory + N'Cp2396g01_group5_db_v1.mdf'')
  LOG ON (NAME = N''Cp2396g01_group5_db_log_v1'',  FILENAME = N''' + @device_directory + N'Cp2396g01_group5_db_v1.ldf'')');
GO
GO

-- Set options for the 'Cp2396g01_group5_db' database depending on the SQL Server version.
IF CAST(SERVERPROPERTY('ProductMajorVersion') AS INT) < 12 
BEGIN
  -- For versions earlier than SQL Server 2014
  ALTER DATABASE [Cp2396g01_group5_db] SET RECOVERY SIMPLE WITH NO_WAIT;
  ALTER DATABASE [Cp2396g01_group5_db] SET AUTO_UPDATE_STATISTICS_ASYNC OFF;
END
ELSE 
BEGIN
  -- For SQL Server 2014 and later
  ALTER DATABASE [Cp2396g01_group5_db] SET RECOVERY SIMPLE WITH NO_WAIT;
  ALTER DATABASE [Cp2396g01_group5_db] SET AUTO_UPDATE_STATISTICS_ASYNC OFF;
END
GO
GO

-- Set quoted identIFy for database
SET quoted_identIFier ON
GO

-- Set the date format to month/day/year.
SET DATEFORMAT mdy;
GO

-- Set the compatibility level for the 'Cp2396g01_group5_db' database.
ALTER DATABASE [Cp2396g01_group5_db] SET COMPATIBILITY_LEVEL = 150;
GO
GO
-- IF Full-Text Indexing is installed, enable it for the 'Cp2396g01_group5_db' database.
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
BEGIN
  EXEC [Cp2396g01_group5_db].[dbo].[sp_fulltext_database] @action = 'enable';
END
GO
GO

-- Set ANSI and ARITHABORT options for the 'Cp2396g01_group5_db' database.
ALTER DATABASE [Cp2396g01_group5_db] SET ANSI_NULL_DEFAULT OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET ANSI_NULLS OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET ANSI_PADDING OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET ANSI_WARNINGS OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET ARITHABORT OFF;
GO
GO

-- Set various options for the 'Cp2396g01_group5_db' database.
-- (ALTER DATABASE statements are alphabetically ordered for clarity)
ALTER DATABASE [Cp2396g01_group5_db] SET ALLOW_SNAPSHOT_ISOLATION OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET AUTO_CLOSE OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET AUTO_SHRINK OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET AUTO_UPDATE_STATISTICS ON;
ALTER DATABASE [Cp2396g01_group5_db] SET DATE_CORRELATION_OPTIMIZATION OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET DB_CHAINING OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET DELAYED_DURABILITY = DISABLED;
ALTER DATABASE [Cp2396g01_group5_db] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF );
ALTER DATABASE [Cp2396g01_group5_db] SET HONOR_BROKER_PRIORITY OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET MULTI_USER;
ALTER DATABASE [Cp2396g01_group5_db] SET NUMERIC_ROUNDABORT OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET PAGE_VERIFY CHECKSUM;
ALTER DATABASE [Cp2396g01_group5_db] SET PARAMETERIZATION SIMPLE;
ALTER DATABASE [Cp2396g01_group5_db] SET QUOTED_IDENTIFIER OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET QUERY_STORE = OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET READ_COMMITTED_SNAPSHOT OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET RECURSIVE_TRIGGERS OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET TARGET_RECOVERY_TIME = 60 SECONDS;
ALTER DATABASE [Cp2396g01_group5_db] SET TRUSTWORTHY OFF;
GO
GO

-- Switch to the 'Cp2396g01_group5_db' database to perform subsequent commands.
USE [Cp2396g01_group5_db];
GO

-- Create entity for the database 'Cp2396g01_group5_db'

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create table PERMISSION--
CREATE TABLE [dbo].[Permissions](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[permissionName] [nvarchar](50) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Permission" PRIMARY KEY  CLUSTERED 
	(
		"ID"ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]
GO
CREATE INDEX idx_perid ON "dbo"."Permissions"("ID");
GO
CREATE INDEX idx_pername ON "dbo"."Permissions"("permissionName");
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create table Encrypt--
CREATE TABLE [dbo].[Encrypt](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[salt] [nvarchar](max) NULL,
	[pepper] [nvarchar](35) NULL,
	CONSTRAINT "PK_Encrypt" PRIMARY KEY  CLUSTERED 
	(
		"ID"ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Manager--
CREATE TABLE [dbo].[Managers](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[manager_gender] [smallint] NULL,
	[manager_group] [bigint] CONSTRAINT "DF_Perr_Mana" DEFAULT (1),
	------------------------------
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](max) NULL,
	--------------------------------
	[fullname] [nvarchar](50) NULL,	
	[address] [nvarchar](255) NULL,
	[phone] [nvarchar](50) NULL,
	[email] [nvarchar](255) NULL,
	[imageURL] [nvarchar](max) NULL,
	-------------------------------
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](50) NULL,
	[modifieby] [nvarchar](50) NULL,
	[status] [bit] NULL CONSTRAINT "DF_Status_Mana" DEFAULT (1),
	CONSTRAINT "PK_Managers" PRIMARY KEY  CLUSTERED 
	(
		"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Manager_Group" FOREIGN KEY 
	(
		"manager_group"
	) REFERENCES "dbo"."Permissions" (
		"ID"
	),	
)ON [Primary]
GO
CREATE INDEX idx_mnagerper ON Managers(manager_group);
GO
CREATE INDEX idx_puser ON Managers(username);
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Employee--
CREATE TABLE [dbo].[Employees](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[employee_gender] [smallint] NULL,
	[employee_group] [bigint] CONSTRAINT "DF_Perr_Emp" DEFAULT (2),
	-----------------------------
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](max) NULL,
	-------------------------------
	[fullname] [nvarchar](50) NULL,
	[address] [nvarchar](255) NULL,
	[phone] [nvarchar](50) NULL,
	[email] [nvarchar](255) NULL,
	[birthDate] [datetime] NULL ,
	[imageURL] [nvarchar](max) NULL,
	------------------------------
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](50) NULL,
	[modifieby] [nvarchar](50) NULL,
	[status] [bit] NULL CONSTRAINT "DF_Status_Emp" DEFAULT (1),
	CONSTRAINT "PK_Employee" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Employee_Group" FOREIGN KEY 
	(
		"employee_group"
	) REFERENCES "dbo"."Permissions" (
		"ID"
	),

	CONSTRAINT "CK_Birthdate" CHECK (birthDate < getdate())
)ON [Primary]
GO
CREATE INDEX idx_empper ON Employees(employee_group);
GO
CREATE INDEX idx_emppuser ON Employees(username);
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Customers--
CREATE TABLE [dbo].[Customers](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[customer_gender] [smallint] NULL,
	[customer_card] [bigint] NULL,
	[customer_group] [bigint] CONSTRAINT "DF_Perr_Cus" DEFAULT (3),
	------------------------------
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](max) NULL,
	-------------------------------
	[fullname] [nvarchar](100) NULL,
	[address] [nvarchar](50) NULL,
	[phone] [nvarchar](50) NULL,
	[email] [nvarchar](150) NULL,
	[birthDate] [datetime] NULL ,
	[imageURL] [nvarchar](max) NULL,
	-------------------------------
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](50) NULL,
	[modifieby] [nvarchar](50) NULL,
	[status] [bit] NULL CONSTRAINT "DF_Status_Cus" DEFAULT (1),
	CONSTRAINT "PK_Customer" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],	
	CONSTRAINT "FK_Customer_Group" FOREIGN KEY 
	(
		"customer_group"
	) REFERENCES "dbo"."Permissions" (
		"ID"
	),
	
	CONSTRAINT "CK_Birthdate_Customer" CHECK (birthDate < getdate()),
	
)ON [Primary]
GO
CREATE INDEX idx_cusper ON Customers(customer_group);
GO
CREATE INDEX idx_cuscard ON Customers(customer_card);
GO
CREATE INDEX idx_cuspuser ON Customers(username);
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Cards--
CREATE TABLE [dbo].[Cards](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[cardName] [nvarchar](255) NULL,
	[cardNumber] [varchar](16) NULL,
	[valueFrom] [datetime] NULL,
	[expirationDate] [dateTime] NULL,
	[cvvNumber] [int] NULL,
	[cardType] [nvarchar](20) NULL,
	[point] [bigint] NULL,
	[rank] [nvarchar](50) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Card" PRIMARY KEY  CLUSTERED 
	(
		"ID" ASC		
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
	CONSTRAINT "CK_cvvNumber" CHECK (LEN(cvvNumber) = 3 AND cvvNumber LIKE '[0-9][0-9][0-9]'),
	CONSTRAINT "CK_CardNumberFormat" CHECK (LEN(cardNumber) = 16 AND cardNumber LIKE '[0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9]')

)ON [Primary]
GO
CREATE INDEX idx_card ON Cards(ID);
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Customer_Card--
CREATE TABLE [dbo].[Customer_Card](
	[customer_ID] [bigint] NOT NULL,
	[card_ID] [bigint] NOT NULL,	
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Customer_Card" PRIMARY KEY  CLUSTERED 
	(
		"customer_ID",
		"card_ID"
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
	CONSTRAINT "FK_CustomerCard_Customer" FOREIGN KEY 
	(
		"customer_ID"
	) REFERENCES "dbo"."Customers" (
		"ID"
	),
	CONSTRAINT "FK_CustomerCard_Card" FOREIGN KEY 
	(
		"card_ID"
	) REFERENCES "dbo"."Cards" (
		"ID"
	),

)ON [Primary]
GO
CREATE INDEX idx_cuscardid ON Customer_Card(customer_ID,card_ID);
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Blogs--
CREATE TABLE [dbo].[Blogs](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[employee_ID] [bigint] NULL,
	[title] [nvarchar](255) NULL,
	[imageURL] [nvarchar](max) NULL,
	[content] [nvarchar](max) NULL,
	[category] [nvarchar](100) NULL,
	[sortcontent] [nvarchar](max) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Feedback" PRIMARY KEY  CLUSTERED 
	(
		"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

)ON [Primary]
GO
CREATE INDEX idx_blog ON Blogs(ID);
GO
CREATE INDEX idx_blogemp ON Blogs(employee_ID);
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table FeedBacks--
CREATE TABLE [dbo].[Feedbacks](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](255) NOT NULL,
	[content] [nvarchar](max) NULL,
	[date] [datetime] NULL,
	[rate] [int] NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Feedback_Cus" PRIMARY KEY  CLUSTERED 
	(
		"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
	
)ON [Primary]

GO



SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Suppliers--
CREATE TABLE [dbo].[Suppliers](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[companyName] [nvarchar] (255) NULL,
	[contactName] [nvarchar] (255) NULL,
	[contactTitle] [nvarchar] (255) NULL,
	[address] [nvarchar](255) NULL,
	[city] [nvarchar](15) NULL,
	[postalCode] [nvarchar](10) NULL,
	[phone] [nvarchar](24) NULL,
	[fax] [nvarchar](24) NULL,
	[homePage] [nvarchar](max) NULL,
	[description] [nvarchar](255) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Supplier" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]
GO
CREATE INDEX idx_supplier ON Suppliers(ID);
GO
CREATE INDEX idx_suppliername ON Suppliers(companyName);
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Brand--
CREATE TABLE [dbo].[Brand](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[supplierID] [bigint] NOT NULL,
	[brandName] [nvarchar](255) NOT NULL,
	[address] [nvarchar](255) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Brand" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
	CONSTRAINT "FK_Brand_Supplier" FOREIGN KEY 
	(
		"supplierID"
	) REFERENCES "dbo"."Suppliers" (
		"ID"
	),
)ON [Primary]
GO
CREATE INDEX idx_brand ON Brand(ID);
GO
GO
CREATE INDEX idx_supplierid ON Brand(supplierID);
GO



SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Branch--
CREATE TABLE [dbo].[Branch](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[companyName] [nvarchar] (40) NULL,
	[branchName] [nvarchar](50)  NULL,
	[address] [nvarchar](255) NULL,
	[city] [nvarchar](15) NULL,
	[phone] [nvarchar](24) NULL,
	[fax] [nvarchar](24) NULL,
	[postalCode] [nvarchar](10) NULL,
	[description] [nvarchar](255) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
CONSTRAINT "PK_Branch" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]
GO
CREATE INDEX idx_branch ON Branch(ID);
GO
 
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Categories--
CREATE TABLE [dbo].[Categories](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[categoryName] [nvarchar](50) NULL,
	[restockThreshold] [int] NULL,
	[newAdjustment] [int]NULL CONSTRAINT "DF_Products_AdjustmentThreshold" DEFAULT (0),
	[description] [nvarchar](255) NULL,
	CONSTRAINT "PK_Category" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]
GO
CREATE INDEX idx_cate ON Categories(ID);
GO



SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Inventory Status--
CREATE TABLE InventoryStatus
(
    [ID] [BIGINT] identity(1,1) NOT NULL,
    [statusName] [NVARCHAR](50) NULL,
	CONSTRAINT "PK_Status" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

)ON [Primary]
GO
CREATE INDEX idx_inven ON InventoryStatus(ID);
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Products--
CREATE TABLE [dbo].[Products](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,	
	[categoryID] [bigint] NOT NULL,
	[brandID] [bigint] NOT NULL,
	[supplierID] [bigint] NOT NULL,

	[productName] [nvarchar](255) NULL,
	[imageURL] [nvarchar](max) NULL,
	[unitPrice] [money] NULL CONSTRAINT "DF_Products_UnitPrice" DEFAULT (0),
	[quantity] [int] NULL,
	[quantityInStock] [int] NULL,
	[unit] [nvarchar](50) NULL,
	[description] [nvarchar](max) NULL,
	[sortdescription] [nvarchar](max) NULL,
	--------------------------------
	[manufactured_place] [nvarchar](255) NULL,
	[sku] [nvarchar](25) NULL,
	[storage_instruction] [nvarchar](100) NULL,
	[usage_notes] [nvarchar](100) NULL,
	[discount] [int] NULL,
	[avaliable] [bit] NULL,
	[inventoryStatus] [bigint] NOT NULL CONSTRAINT "DF_Inventory" DEFAULT (1),

	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Products" PRIMARY KEY  CLUSTERED 
	(
		"ID"
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Products_Categories" FOREIGN KEY 
	(
		"categoryID"
	) REFERENCES "dbo"."Categories" (
		"ID"
	),
	CONSTRAINT "FK_Products_Brands" FOREIGN KEY 
	(
		"brandID"
	) REFERENCES "dbo"."Brand" (
		"ID"
	),
	CONSTRAINT "FK_Products_Suppliers" FOREIGN KEY 
	(
		"supplierID"
	) REFERENCES "dbo"."Suppliers" (
		"ID"
	),
	CONSTRAINT "FK_Products_InventoryStatus" FOREIGN KEY 
	(
		"inventoryStatus"
	) REFERENCES "dbo"."InventoryStatus" (
		"ID"
	),

)On [Primary]
GO
CREATE INDEX idx_product ON Products(ID);
GO
GO
CREATE INDEX idx_productsupport ON Products(categoryID,brandID,supplierID);
GO
GO
CREATE INDEX idx_productdetail ON Products(productName,unitPrice,discount);
GO
GO
CREATE INDEX idx_productstock ON Products(quantity,quantityInStock,inventoryStatus);
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table FeedBacks--
CREATE TABLE [dbo].[Feedbacks_Pro](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[customerID][bigint]NOT NULL,
	[productID][bigint]NOT NULL,
	[title] [nvarchar](255) NOT NULL,
	[content] [nvarchar](max) NULL,
	[date] [datetime] NULL,
	[rate] [int] NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Feedback_Pro" PRIMARY KEY  CLUSTERED 
	(
		"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
	CONSTRAINT "FK_CustomerFeedback_Customer" FOREIGN KEY 
	(
		"customerID"
	) REFERENCES "dbo"."Customers" (
		"ID"
	),
	CONSTRAINT "FK_ProductFeedback_Product" FOREIGN KEY 
	(
		"productID"
	) REFERENCES "dbo"."Products" (
		"ID"
	),
)ON [Primary]
GO
CREATE INDEX idx_fp ON Feedbacks_Pro(customerID,productID);
GO
CREATE INDEX idx_fpi ON Feedbacks_Pro(ID);
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Import--
	CREATE TABLE [dbo].[ImportOrder](
		[ID] [bigint] IDENTITY(1,1) NOT NULL,
		[productID][bigint]NOT NULL,
		[branchID][bigint]NOT NULL,
		[dateImport] [datetime] NULL,
		[hsCode] [int] NULL,
		[amountDelivery] [int] NULL,
		[description] [nvarchar](255) NULL,
		[status] [nvarchar](50) NULL,
		[leadtime] [int] NULL,
		[createdate] [datetime] NULL,
		[modifiedate] [datetime] NULL,
		[createby] [nvarchar](255) NULL,
		[modifieby] [nvarchar](255) NULL
		CONSTRAINT "PK_Import" PRIMARY KEY CLUSTERED
		(
		"ID" ASC
		)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

		CONSTRAINT "FK_Import_Product" FOREIGN KEY 
		(
			"productID"
		) REFERENCES "dbo"."Products" (
			"ID"
		),

		CONSTRAINT "FK_Import_Branch" FOREIGN KEY 
		(
			"branchID"
		) REFERENCES "dbo"."Branch" (
			"ID"
		),		

)ON [Primary]
GO
CREATE INDEX idx_io ON ImportOrder(productID,branchID);
GO
CREATE INDEX idx_ioi ON ImportOrder(ID);
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table ThAdj--
CREATE TABLE [dbo].[ThresholdAdjustment](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[categoryID] [bigint] NOT NULL,
	[reasonAdjustment] [nvarchar](50) NULL,
	[new_restockThreshold] [int] NULL,
	[dateAdjusted] [datetime] NULL,
	[statusThresholdAdjustments] [nvarchar](50) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Adjustment" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Adjustment_WP" FOREIGN KEY 
	(
		"categoryID"
	) REFERENCES "dbo"."Categories" (
		"ID"
	),
)On [Primary]
GO
CREATE INDEX idx_ta ON ThresholdAdjustment(categoryID);
GO
CREATE INDEX idx_tai ON ThresholdAdjustment(ID);
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Orders--
CREATE TABLE dbo.Orders (
    [ID] [bigINT] IDENTITY(1,1) NOT NULL,
    [customerID] [bigINT] NOT NULL,
	[employeeID] [bigINT] NOT NULL,   	
	[paymentMethod][smallint] NULL,
	[note] [nvarchar](max) NULL,
    [status] [int] NULL,
	CONSTRAINT "PK_Orders" PRIMARY KEY CLUSTERED 
	(
	ID ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

    CONSTRAINT "FK_Orders_Customers" FOREIGN KEY ("customerID") REFERENCES "dbo"."Customers"("ID"),
	CONSTRAINT "FK_Orders_Employees" FOREIGN KEY ("employeeID") REFERENCES "dbo"."Employees"("ID")
)ON [Primary]
GO
CREATE INDEX idx_order ON Orders(customerID,employeeID);
GO
CREATE INDEX idx_orderid ON Orders(ID);
GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table OrderDetails
CREATE TABLE [dbo].[OrderDetails](	
	[orderID] [bigint]NOT NULL,
	[productID][bigint]NOT NULL,
	[unitPrice] [money] NULL,
	[quantity] [int]NULL,
	[discount] [int]NULL,
	[totalPrice] [money] NULL,
	[orderDate] [DATETIME] NULL,
    [shipDate] [DATETIME] NULL,
	[shipAddress] [nvarchar](255) NULL,
	CONSTRAINT "PK_Order_Details" PRIMARY KEY  CLUSTERED 
	(
		"orderID",
		"productID"
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Order_Details_Orders" FOREIGN KEY 
	(
		"orderID"
	) REFERENCES "dbo"."Orders" (
		"ID"
	),
	CONSTRAINT "FK_Order_Details_Pro" FOREIGN KEY 
	(
		"productID"
	) REFERENCES "dbo"."Products" (
		"ID"
	),
)ON [Primary]
GO
CREATE INDEX idx_orderd ON OrderDetails(orderID,productID);
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Blog_category
CREATE TABLE [dbo].[Blog_Categories](	
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[name][nvarchar](50) NULL,
	[description] [nvarchar](150) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Blog_Categories" PRIMARY KEY  CLUSTERED 
	(
		"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

)ON [Primary]
GO
CREATE INDEX idx_bc ON Blog_Categories(ID);
GO


-- Create view OrderByQuarter
CREATE VIEW OrderByQuarter AS
SELECT
    ID,
    CustomerID,
    EmployeeID,
    OrderDate,
    ShipDate,
    ShipAddress,
    Note,
    Status,
    FullName,
    Address,
    Phone,
    Email,
    Point,
    Rank,
    CASE
        WHEN MONTH(OrderDate) BETWEEN 1 AND 3 THEN 'Q1'
        WHEN MONTH(OrderDate) BETWEEN 4 AND 6 THEN 'Q2'
        WHEN MONTH(OrderDate) BETWEEN 7 AND 9 THEN 'Q3'
        WHEN MONTH(OrderDate) BETWEEN 10 AND 12 THEN 'Q4'
        ELSE NULL
    END AS Quarter
FROM
    (
        -- Your existing join with Customers and CreditCard
        SELECT
            Orders.ID,
            Orders.CustomerID,
            Orders.EmployeeID,
            OrderDetails.OrderDate,
            OrderDetails.ShipDate,
            OrderDetails.ShipAddress,
            Orders.Note,
            Orders.Status,
            Customers.FullName,
            Customers.Address,
            Customers.Phone,
            Customers.Email,
            Cards.Point,
            Cards.Rank
         FROM
            Customers
            INNER JOIN Orders ON Customers.ID = Orders.CustomerID
            INNER JOIN Customer_Card ON Customer_Card.customer_ID = Customers.ID
            INNER JOIN Cards ON Cards.ID = Customer_Card.card_ID
            INNER JOIN OrderDetails ON OrderDetails.orderID = Orders.ID
    ) AS Subquery;

GO
GO

-- Create view Invoices
CREATE VIEW Invoices AS
SELECT 
    o.ID AS 'Invoice Number',
    od.orderDate AS 'Order Date',
    c.fullName AS 'Customer Name',
    c.address AS 'Customer Address',
    c.phone AS 'Customer Phone',
    od.productID AS 'Product ID',
    p.productName AS 'Product Name',
    od.unitPrice AS 'Unit Price',
    od.quantity AS 'Quantity',
    od.discount AS 'Discount (%)',
    od.totalPrice AS 'Line Total',
    od.shipAddress AS 'Shipping Address',
    o.note AS 'Notes',
    o.status AS 'Order Status',
    o.paymentMethod AS 'Payment Method',
    cd.cardName AS 'Card Name',
    cd.cardNumber AS 'Card Number (last 4 digits)',
    (SELECT SUM(totalPrice) FROM OrderDetails WHERE orderID = o.ID) AS 'Invoice Total'
FROM Orders o
INNER JOIN Customers c ON o.customerID = c.ID
INNER JOIN OrderDetails od ON o.ID = od.orderID
INNER JOIN Products p ON od.productID = p.ID
INNER JOIN Customer_Card cc ON cc.customer_ID = c.ID
INNER JOIN Cards cd ON cd.ID = cc.card_ID
WHERE o.status = 'Successful';

GO

-- Create view Order Subtotals
CREATE VIEW "Order Subtotals" AS
SELECT
    OD.OrderID,
    SUM(CONVERT(money, (P.unitPrice * OD.quantity * (1 - OD.Discount) / 100)) * 100) AS Subtotal
FROM
    dbo.OrderDetails AS OD
JOIN
    dbo.Products AS P ON P.ID = OD.productID
GROUP BY
    OD.OrderID;
GO
GO

-- Create view Sales Totals by Amount
CREATE VIEW "Sales Totals by Amount" AS
SELECT 
    SUM("Order Subtotals".Subtotal) AS SaleAmount, 
    Orders.ID, 
    Customers.fullname, 
    OrderDetails.ShipDate,
    DATEPART(QUARTER, OrderDetails.ShipDate) AS Quarter
FROM 
    Customers 
    INNER JOIN Orders ON Customers.ID = Orders.CustomerID
	INNER JOIN OrderDetails ON Orders.ID = OrderDetails.orderID
    INNER JOIN "Order Subtotals" ON Orders.ID = "Order Subtotals".OrderID
WHERE 
    ("Order Subtotals".Subtotal > 2500)
GROUP BY 
    Orders.ID, 
    Customers.fullname, 
    OrderDetails.ShipDate, 
    DATEPART(QUARTER, OrderDetails.ShipDate);
GO
GO

-- Create procedure Sales by Year
create procedure "Sales by Year" 
	@Beginning_Date DateTime, @Ending_Date DateTime AS
SELECT OrderDetails.ShipDate, Orders.ID, "Order Subtotals".Subtotal, DATENAME(yy,ShipDate) AS Year
FROM Orders 
INNER JOIN "Order Subtotals" ON Orders.ID = "Order Subtotals".OrderID
INNER JOIN OrderDetails ON Orders.ID = OrderDetails.orderID
WHERE OrderDetails.ShipDate Between @Beginning_Date And @Ending_Date
GO
GO

------Enforce quantity <= quantityInStock------
CREATE TRIGGER trg_Products_UpdateQuantityInStock
ON Products
AFTER INSERT, UPDATE
AS
BEGIN
    IF UPDATE(quantity)
    BEGIN
        UPDATE Products
        SET quantityInStock = quantity
        WHERE ID IN (SELECT ID FROM inserted);
    END
END;
GO
 
 -----------Enforce restockThreshold in Categories table----------
CREATE TRIGGER trg_UpdateRestockThreshold
ON ThresholdAdjustment
AFTER INSERT
AS
BEGIN
    IF UPDATE(statusThresholdAdjustments)
        AND EXISTS (SELECT 1 FROM inserted WHERE statusThresholdAdjustments = 'Accept')
    BEGIN
        UPDATE Categories
        SET restockThreshold = newAdjustment,  -- Update restockThreshold using newAdjustment
            newAdjustment = i.new_restockThreshold  -- Capture new_restockThreshold for future reference
        FROM inserted i
        WHERE Categories.ID = i.categoryID;
    END
END;
GO

------Enforce group manager------
CREATE TRIGGER trg_Group_Manager
ON Managers AFTER INSERT AS BEGIN
UPDATE Managers
SET
  manager_group = 1
FROM
  Managers
  INNER JOIN inserted ON Managers.ID = inserted.ID END;
GO

------Enforce group employee------
CREATE TRIGGER trg_Group_Employee
ON Employees AFTER INSERT AS BEGIN
UPDATE Employees
SET
  employee_group = 2
FROM
  Employees
  INNER JOIN inserted ON Employees.ID = inserted.ID END;
GO

------Enforce group customer------
CREATE TRIGGER trg_Group_Customer
ON Customers AFTER INSERT AS BEGIN
UPDATE Customers
SET
  customer_group = 3
FROM
  Customers
  INNER JOIN inserted ON Customers.ID = inserted.ID END;
GO

------Enforce UpdateInventoryStatus------
CREATE TRIGGER Trigger_UpdateInventoryStatus ON Products AFTER INSERT,
UPDATE AS BEGIN
UPDATE Products
SET
  inventoryStatus = CASE
    WHEN Products.quantityInStock < 5 THEN 3
    WHEN Products.quantityInStock >= 50 THEN 2
    WHEN Products.quantityInStock > 100 THEN 1
  END
FROM
  Products
  INNER JOIN inserted ON Products.ID = inserted.ID;
END;
GO

 
 --=======================INSERT DATA=====================--
set quoted_identifier on
go
ALTER TABLE "Permissions" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Permissions" ("permissionName", createdate) VALUES ('manager', GETDATE());
INSERT INTO "Permissions" ("permissionName", createdate) VALUES ('employee', GETDATE());
INSERT INTO "Permissions" ("permissionName", createdate) VALUES ('customer', GETDATE());
GO
ALTER TABLE "Permissions" CHECK CONSTRAINT ALL
go
GO

-------------------------------------------------------------
set quoted_identifier on
go
ALTER TABLE "blog_categories" NOCHECK CONSTRAINT ALL
go
INSERT INTO "blog_categories" (name, description, createdate) VALUES('Cuisine','cuisine', GETDATE());
INSERT INTO "blog_categories" (name, description, createdate) VALUES('Culture','culture', GETDATE());
INSERT INTO "blog_categories" (name, description, createdate) VALUES('Travel','travel', GETDATE());
INSERT INTO "blog_categories" (name, description, createdate) VALUES('Social life','Social life', GETDATE());
GO
ALTER TABLE "blog_categories" CHECK CONSTRAINT ALL
go
GO
--------------------------------------
set quoted_identifier on
go
ALTER TABLE "Suppliers" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Suppliers" (companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES ('GO! VIETNAM', 'John Doe', 'Manager', 'Ninh Kieu', 'Can Tho', '900000', '555-1234', '555-5678', 'https://go-vietnam.vn/', 'Supplier Description');
INSERT INTO "Suppliers" (companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES ('WinCommerce General Trading Services Joint Stock Company', 'Jane Smith', 'Sales Representative', 'Binh Thuy', 'Can Tho', '900000', '67890', '555-9876', 'https://winmart.vn/', 'Supplier Description');
INSERT INTO "Suppliers" (companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES ('The CocaCola Company', 'Bob Johnson', 'Owner', 'Thot Not', 'Can Tho', '900000', '555-1111', '555-2222', 'https://www.coca-cola.com/vn/vi', 'Supplier Description');
INSERT INTO "Suppliers" (companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES ('Bach Hoa Xanh Trading Joint Stock Company', 'Alice Johnson', 'Sales Manager', 'Cai Rang', 'Can Tho', '900000', '555-3333', '555-4444', 'https://www.bachhoaxanh.com/', 'Supplier Description');
INSERT INTO "Suppliers" (companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES ('LOTTE VIETNAM SHOPPING JOINT STOCK COMPANY', 'Charlie Brown', 'Marketing Coordinator', 'O Mon', 'Can Tho', '900000', '555-5555', '555-6666', 'https://www.lottemart.vn/', 'Supplier Description');

GO
ALTER TABLE "Suppliers" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
ALTER TABLE "Brand" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Brand" ( supplierID ,brandName, address) VALUES (1,'Toshiba', 'Can Tho');
INSERT INTO "Brand" ( supplierID ,brandName, address) VALUES (2, 'Mitsubishi', 'Can Tho');
INSERT INTO "Brand" ( supplierID ,brandName, address) VALUES (3, 'NowFood', 'Can Tho');
INSERT INTO "Brand" ( supplierID ,brandName, address) VALUES (4, 'Green Air', 'Can Tho');
INSERT INTO "Brand" ( supplierID ,brandName, address) VALUES (5, 'Golden Health', 'Can Tho');
INSERT INTO "Brand" ( supplierID ,brandName, address) VALUES (5, 'Juice', 'Can Tho');


GO
ALTER TABLE "Brand" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
ALTER TABLE "Branch" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Branch" ( companyName, branchName, address, city, phone, fax, postalCode, description) VALUES ('ZipMart co.', 'ZipMart Ninh Kieu', 'Ninh Kieu', 'Can Tho', '555-1111', '555-2222', '900000', 'Ninh Kieu Branch');
INSERT INTO "Branch" ( companyName, branchName, address, city, phone, fax, postalCode, description) VALUES ('ZipMart co.', 'ZipMart Binh Thuy', 'Ninh Kieu', 'Can Tho', '555-1111', '555-2222', '900000', 'Ninh Kieu Branch');
INSERT INTO "Branch" ( companyName, branchName, address, city, phone, fax, postalCode, description) VALUES ('ZipMart co.', 'ZipMart Cai Rang', 'Ninh Kieu', 'Can Tho', '555-1111', '555-2222', '900000', 'Ninh Kieu Branch');
INSERT INTO "Branch" ( companyName, branchName, address, city, phone, fax, postalCode, description) VALUES ('ZipMart co.', 'ZipMart Phong Dien', 'Ninh Kieu', 'Can Tho', '555-1111', '555-2222', '900000', 'Ninh Kieu Branch');

GO
ALTER TABLE "Branch" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
ALTER TABLE "Categories" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Categories" ("CategoryName", "Description", "restockThreshold", "newAdjustment") VALUES ('Household Appliances', 'Home electronic devices', 500,0);
INSERT INTO "Categories" ("CategoryName", "Description", "restockThreshold", "newAdjustment") VALUES ('Spices', 'Various cooking spices', 500,0);
INSERT INTO "Categories" ("CategoryName", "Description", "restockThreshold", "newAdjustment") VALUES ('Meat', 'Various meat products', 500,0);
INSERT INTO "Categories" ("CategoryName", "Description", "restockThreshold", "newAdjustment") VALUES ('Seafood', 'Various seafood products', 500,0);
INSERT INTO "Categories" ("CategoryName", "Description", "restockThreshold", "newAdjustment") VALUES ('Vegetables', 'Fresh vegetables', 500,0);
INSERT INTO "Categories" ("CategoryName", "Description", "restockThreshold", "newAdjustment") VALUES ('Convenience Products', 'Convenient and ready-to-use products', 500,0);
INSERT INTO "Categories" ("CategoryName", "Description", "restockThreshold", "newAdjustment") VALUES ('Fashion', 'Clothing and accessories', 500,0);
INSERT INTO "Categories" ("CategoryName", "Description", "restockThreshold", "newAdjustment") VALUES ('Fruits', 'Fresh fruits', 500,0);
GO
ALTER TABLE "Categories" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
ALTER TABLE "InventoryStatus" NOCHECK CONSTRAINT ALL
go
INSERT INTO dbo.InventoryStatus (statusName) VALUES ('INSTOCK');
INSERT INTO dbo.InventoryStatus (statusName) VALUES ('LOWSTOCK');
INSERT INTO dbo.InventoryStatus (statusName) VALUES ('OUTOFSTOCK');

GO
ALTER TABLE "InventoryStatus" CHECK CONSTRAINT ALL
go
GO
----------------------------------
set quoted_identifier on
go
ALTER TABLE "Encrypt" NOCHECK CONSTRAINT ALL
go
INSERT INTO dbo.Encrypt(salt,pepper) VALUES ('skdpowkmldoinkjthdg12jdn23','supermarketzipmart');

GO
ALTER TABLE "Encrypt" CHECK CONSTRAINT ALL
go
GO

GO
USE [master]
GO
ALTER DATABASE [Cp2396g01_group5_db] SET  READ_WRITE 
GO
