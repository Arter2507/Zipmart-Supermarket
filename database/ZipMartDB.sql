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
  ON PRIMARY (NAME = N''Cp2396g01_group5_db'', FILENAME = N''' + @device_directory + N'Cp2396g01_group5_db.mdf'')
  LOG ON (NAME = N''Cp2396g01_group5_db_log'',  FILENAME = N''' + @device_directory + N'Cp2396g01_group5_db.ldf'')');
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
GO

IF exists (select * from sysobjects where id = object_id('dbo.Sales by Year') and sysstat & 0xf = 4)
	drop procedure [dbo].[Sales by Year]
GO

IF exists (select * from sysobjects where id = object_id('dbo.OrderByQuarter') and sysstat & 0xf = 2)
	drop view [dbo].[OrderByQuarter]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Sales Totals by Amount') and sysstat & 0xf = 2)
	drop view [dbo].[Sales Totals by Amount]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Invoice') and sysstat & 0xf = 2)
	drop view [dbo].[InvoiceView]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Order Subtotals') and sysstat & 0xf = 2)
	drop view [dbo].[Order Subtotals]
GO

IF exists (select * from sysobjects where id = object_id('dbo.BlogFeedBacks') and sysstat & 0xf = 3)
	drop table [dbo].[BlogFeedBacks]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Blogs') and sysstat & 0xf = 3)
	drop table [dbo].[Blogs]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Branch') and sysstat & 0xf = 3)
	drop table [dbo].[Branch]
GO

IF exists (select * from sysobjects where id = object_id('dbo.ImportOrder') and sysstat & 0xf = 3)
	drop table [dbo].[ImportOrder]
GO

IF exists (select * from sysobjects where id = object_id('dbo.ThresholdAdjustment') and sysstat & 0xf = 3)
	drop table [dbo].[ThresholdAdjustment]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Orders') and sysstat & 0xf = 3)
	drop table [dbo].[Orders]
GO

IF exists (select * from sysobjects where id = object_id('dbo.OrdersDetails') and sysstat & 0xf = 3)
	drop table [dbo].[OrdersDetails]
GO

IF exists (select * from sysobjects where id = object_id('dbo.SalesTarget') and sysstat & 0xf = 3)
	drop table [dbo].[SalesTarget]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Managers') and sysstat & 0xf = 3)
	drop table [dbo].[Managers]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Employees') and sysstat & 0xf = 3)
	drop table [dbo].[Employees]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Products') and sysstat & 0xf = 3)
	drop table [dbo].[Products]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Feedbacks') and sysstat & 0xf = 3)
	drop table [dbo].[Feedbacks]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Customers') and sysstat & 0xf = 3)
	drop table [dbo].[Customers]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Accounts') and sysstat & 0xf = 3)
	drop table [dbo].[Accounts]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Permission') and sysstat & 0xf = 3)
	drop table [dbo].[Permission]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Suppliers') and sysstat & 0xf = 3)
	drop table [dbo].[Suppliers]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Brand') and sysstat & 0xf = 3)
	drop table [dbo].[Brand]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Categories') and sysstat & 0xf = 3)
	drop table [dbo].[Categories]
GO

IF exists (select * from sysobjects where id = object_id('dbo.InventoryStatus') and sysstat & 0xf = 3)
	drop table [dbo].[InventoryStatus]
GO



SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create table PERMISSION--
CREATE TABLE [dbo].[Permission](
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

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Account--
CREATE TABLE [dbo].[Accounts](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[permissionID] [bigint] CONSTRAINT "DF_Perr" DEFAULT (3),
	[description] [nvarchar](255) NULL,
	[status] [bit] CONSTRAINT "DF_Sta" DEFAULT (1),
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Accounts" PRIMARY KEY  CLUSTERED 
	(
		"ID"ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Accounts_Permission" FOREIGN KEY 
	(
		"permissionID"
	) REFERENCES "dbo"."Permission" (
		"ID"
	),
)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Manager--
CREATE TABLE [dbo].[Managers](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[accountID] [bigint] NOT NULL,
	[fullname] [nvarchar](50) NULL,
	[address] [nvarchar](255) NULL,
	[phone] [nvarchar](50) NULL,
	[email] [nvarchar](255) NULL,
	[imageURL] [nvarchar](max) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Managers" PRIMARY KEY  CLUSTERED 
	(
		"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Manager_Accounts" FOREIGN KEY 
	(
		"accountID"
	) REFERENCES "dbo"."Accounts" (
		"ID"
	),
)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Employee--
CREATE TABLE [dbo].[Employees](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[accountID] [bigint] NOT NULL,
	[fullname] [nvarchar](50) NULL,
	[address] [nvarchar](255) NULL,
	[phone] [nvarchar](50) NULL,
	[email] [nvarchar](255) NULL,
	[birthDate] [datetime] NULL ,
	[Notes] [nvarchar](max) NULL,
	[imageURL] [nvarchar](max) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Employee" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Employee_Account" FOREIGN KEY 
	(
	"accountID"
	) REFERENCES "dbo"."Accounts"
	(
	"ID"
	),
	CONSTRAINT "CK_Birthdate" CHECK (birthDate < getdate())
)ON [Primary]

GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Customers--
CREATE TABLE [dbo].[Customers](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[accountID] [bigint] NULL,
	[fullname] [nvarchar](255) NULL,
	[address] [nvarchar](50) NULL,
	[phone] [nvarchar](50) NULL,
	[email] [nvarchar](255) NULL,
	[birthDate] [datetime] NULL ,
	[imageURL] [nvarchar](255) NULL,
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
	CONSTRAINT "PK_Customer" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],	
	CONSTRAINT "FK_Customer_Account" FOREIGN KEY 
	(
	"accountID"
	) REFERENCES "dbo"."Accounts"
	(
	"ID"
	),
	CONSTRAINT "CK_Birthdate_Customer" CHECK (birthDate < getdate()),
	CONSTRAINT "CK_cvvNumber" CHECK (LEN(cvvNumber) = 3 AND cvvNumber LIKE '[0-9][0-9][0-9]'),
	CONSTRAINT "CK_CardNumberFormat" CHECK (LEN(cardNumber) = 16 AND cardNumber LIKE '[0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9]')
)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Blogs--
CREATE TABLE [dbo].[Blogs](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[employeeID] [bigint] NOT NULL,
	[title] [nvarchar](255) NULL,
	[imageURL] [nvarchar](max) NULL,
	[content] [nvarchar](max) NULL,
	[category] [nvarchar](255) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Feedback" PRIMARY KEY  CLUSTERED 
	(
		"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Blog_Employee" FOREIGN KEY 
	(
		"employeeID"
	) REFERENCES "dbo"."Employees" (
		"ID"
	),
)ON [Primary]

GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table FeedBacks--
CREATE TABLE [dbo].[Feedbacks](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[customerID] [bigint] NOT NULL,
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

	CONSTRAINT "FK_FeedBack_Customer" FOREIGN KEY 
	(
		"customerID"
	) REFERENCES "dbo"."Customers" (
		"ID"
	),
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

--SET ANSI_NULLS ON
--GO
--SET QUOTED_IDENTIFIER ON
--GO
----Create Table Warehouse--
--CREATE TABLE [dbo].[Warehouse](
--	[ID] [bigint] IDENTITY(1,1) NOT NULL,
--	[companyName] [nvarchar] (40) NULL,
--	[warehouseName] [nvarchar](50)  NULL,
--	[address] [nvarchar](255) NULL,
--	[city] [nvarchar](15) NULL,
--	[phone] [nvarchar](24) NULL,
--	[fax] [nvarchar](24) NULL,
--	[postalCode] [nvarchar](10) NULL,
--	[description] [nvarchar](255) NULL,
--	[createdate] [datetime] NULL,
--	[modifiedate] [datetime] NULL,
--	[createby] [nvarchar](255) NULL,
--	[modifieby] [nvarchar](255) NULL
--	CONSTRAINT "PK_Warehouse" PRIMARY KEY CLUSTERED
--	(
--	"ID" ASC
--	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
--)ON [Primary]

--GO


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

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Import--
	CREATE TABLE [dbo].[ImportOrder](
		[ID] [bigint] IDENTITY(1,1) NOT NULL,
		[supplierID] [bigint] NOT NULL,
		[categoryID] [bigint] NOT NULL,
		[branchID] [bigint] NOT NULL,
		[companyName] [nvarchar] (40) NULL,
		[dateImport] [datetime] NULL,
		[hsCode] [int] NULL,
		[nameProduct] [nvarchar](255) NULL,
		[amountDelivery] [int] NULL,
		[address] [nvarchar](255) NULL,
		[city] [nvarchar](15) NULL,
		[phone] [nvarchar](24) NULL,
		[fax] [nvarchar](24) NULL,
		[postalCode] [nvarchar](10) NULL,
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

		CONSTRAINT "FK_Import_Supplier" FOREIGN KEY 
		(
			"supplierID"
		) REFERENCES "dbo"."Suppliers" (
			"ID"
		),

		CONSTRAINT "FK_Import_Category" FOREIGN KEY 
		(
			"categoryID"
		) REFERENCES "dbo"."Categories" (
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
	[viewCount] [int] NULL,
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


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Orders--
CREATE TABLE dbo.Orders (
    [ID] [bigINT] IDENTITY(1,1) NOT NULL,
    [customerID] [bigINT] NOT NULL,
	[employeeID] [bigINT] NOT NULL,
    [orderDate] [DATETIME] NULL,
    [shipDate] [DATETIME] NULL,
	[shipAddress] [nvarchar](255) NULL,
	[note] [nvarchar](max) NULL,
    [status] [NVARCHAR](50) NULL DEFAULT 'Successful',
	CONSTRAINT "PK_Orders" PRIMARY KEY CLUSTERED 
	(
	ID ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

    CONSTRAINT "FK_Orders_Customers" FOREIGN KEY ("customerID") REFERENCES "dbo"."Customers"("ID"),
	CONSTRAINT "FK_Orders_Employees" FOREIGN KEY ("employeeID") REFERENCES "dbo"."Employees"("ID")
)ON [Primary]

GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table OrderDetails
CREATE TABLE [dbo].[OrdersDetails](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[orderID] [bigint]NOT NULL,
	[productID][bigint]NOT NULL,
	[unitPrice] [money] NULL,
	[quantity] [int]NULL,
	[discount] [int]NULL,
	[totalPrice] [money] NULL,
	[paymentMethod][nvarchar](255) NULL,
	[cardName] [nvarchar](255) NULL,
	[cardNumber] [varchar](16)
	CONSTRAINT "PK_Order_Details" PRIMARY KEY  CLUSTERED 
	(
		"ID" ASC
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
)

GO

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
            Orders.OrderDate,
            Orders.ShipDate,
            Orders.ShipAddress,
            Orders.Note,
            Orders.Status,
            Customers.FullName,
            Customers.Address,
            Customers.Phone,
            Customers.Email,
            Customers.Point,
            Customers.Rank
        FROM
            Customers
        INNER JOIN Orders ON Customers.ID = Orders.CustomerID
    ) AS Subquery;

GO
GO

CREATE VIEW Invoices AS
SELECT 
    o.ID AS 'Invoice Number',
    o.orderDate AS 'Order Date',
    c.fullName AS 'Customer Name',
    c.address AS 'Customer Address',
    c.phone AS 'Customer Phone',
    od.productID AS 'Product ID',
    p.productName AS 'Product Name',
    od.unitPrice AS 'Unit Price',
    od.quantity AS 'Quantity',
    od.discount AS 'Discount (%)',
    od.totalPrice AS 'Line Total',
    o.shipAddress AS 'Shipping Address',
    o.note AS 'Notes',
    o.status AS 'Order Status',
    od.paymentMethod AS 'Payment Method',
    od.cardName AS 'Card Name',
    od.cardNumber AS 'Card Number (last 4 digits)',
    (SELECT SUM(totalPrice) FROM OrdersDetails WHERE orderID = o.ID) AS 'Invoice Total'
FROM Orders o
INNER JOIN Customers c ON o.customerID = c.ID
INNER JOIN OrdersDetails od ON o.ID = od.orderID
INNER JOIN Products p ON od.productID = p.ID
WHERE o.status = 'Successful';

GO

CREATE VIEW "Order Subtotals" AS
SELECT
    OD.OrderID,
    SUM(CONVERT(money, (P.unitPrice * OD.quantity * (1 - OD.Discount) / 100)) * 100) AS Subtotal
FROM
    dbo.OrdersDetails AS OD
JOIN
    dbo.Products AS P ON P.ID = OD.productID
GROUP BY
    OD.OrderID;
GO
GO

CREATE VIEW "Sales Totals by Amount" AS
SELECT 
    SUM("Order Subtotals".Subtotal) AS SaleAmount, 
    Orders.ID, 
    Customers.fullname, 
    Orders.ShipDate,
    DATEPART(QUARTER, Orders.ShipDate) AS Quarter
FROM 
    Customers 
    INNER JOIN Orders ON Customers.ID = Orders.CustomerID
    INNER JOIN "Order Subtotals" ON Orders.ID = "Order Subtotals".OrderID
WHERE 
    ("Order Subtotals".Subtotal > 2500)
GROUP BY 
    Orders.ID, 
    Customers.fullname, 
    Orders.ShipDate, 
    DATEPART(QUARTER, Orders.ShipDate);
GO
GO



create procedure "Sales by Year" 
	@Beginning_Date DateTime, @Ending_Date DateTime AS
SELECT Orders.ShipDate, Orders.ID, "Order Subtotals".Subtotal, DATENAME(yy,ShipDate) AS Year
FROM Orders INNER JOIN "Order Subtotals" ON Orders.ID = "Order Subtotals".OrderID
WHERE Orders.ShipDate Between @Beginning_Date And @Ending_Date
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

--=======================INSERT DATA=====================--
set quoted_identifier on
go
ALTER TABLE "Permission" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Permission" ("permissionName") VALUES ('manager');
INSERT INTO "Permission" ("permissionName") VALUES ('employee');
INSERT INTO "Permission" ("permissionName") VALUES ('customer');
GO
ALTER TABLE "Permission" CHECK CONSTRAINT ALL
go
GO

-------------------------------------------
set quoted_identifier on
go
ALTER TABLE "Accounts" NOCHECK CONSTRAINT ALL
go
---User with permission 1-----
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('Admin', 'Admin', 1, 'Manager');
---User with permission 2-----
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('quangtuong', 'Admin', 2, 'Employee');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('hieutho', 'Admin', 2, 'Employee');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('huunhan', 'Admin', 2, 'Employee');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('minhnghia', 'Admin', 2, 'Employee');
---User with permission 3-----
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('hieutho', 'user', 3, 'Customer');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('huunhan', 'user', 3, 'Customer');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('minhnghia', 'user', 3, 'Customer');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('minhtuan', 'user', 3, 'Customer');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('myhao', 'user', 3, 'Customer');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('kimthanh', 'user', 3, 'Customer');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('trongtri', 'user', 3, 'Customer');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('tanphat', 'user', 3, 'Customer');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('ngocanh', 'user', 3, 'Customer');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('anhthu', 'user', 3, 'Customer');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('vanhien', 'user', 3, 'Customer');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('ngoclan', 'user', 3, 'Customer');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('ngoctrang', 'user', 3, 'Customer');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('ngochien', 'user', 3, 'Customer');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('thanhdat', 'user', 3, 'Customer');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('minhdat', 'user', 3, 'Customer');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('thuthao', 'user', 3, 'Customer');
INSERT INTO "Accounts" ("userName", "password", "permissionID", "description") VALUES ('kimtuyen', 'user', 3, 'Customer');
GO
ALTER TABLE "Accounts" CHECK CONSTRAINT ALL
go
GO

-------------------------------------------
set quoted_identifier on
go
ALTER TABLE "Managers" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Managers" ("fullname","accountID","address","email","imageURL","phone") VALUES ('Manager',1,'Can Tho','manager.zipmart@gamil.com','https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/een5b4xmq5twvne8ce0c',' 0292 3731 072');
GO
ALTER TABLE "Managers" CHECK CONSTRAINT ALL
go
GO

-------------------------------------------
set quoted_identifier on
go
ALTER TABLE "Employees" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Employees" ("fullname","accountID","imageURL","birthDate","address","email","phone","Notes") VALUES ('To Quang Tuong',2,'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/pjfdcg0pm9beusibmr3h','07-25-2003','Can Tho','qtuong.257@gmail.com','0917895327','My note');
INSERT INTO "Employees" ("fullname","accountID","imageURL","birthDate","address","email","phone","Notes") VALUES ('Pham Hieu Tho',3,'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn','07-04-2003','Can Tho','hieutho1510@gmail.com','0938973817','My note');
INSERT INTO "Employees" ("fullname","accountID","imageURL","birthDate","address","email","phone","Notes") VALUES ('Pham Huu Nhan',4,'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/een5b4xmq5twvne8ce0c','07-25-2003','Can Tho','huunhan.service@gmail.com','0123456789','Off');
INSERT INTO "Employees" ("fullname","accountID","imageURL","birthDate","address","email","phone","Notes") VALUES ('Ly Minh Nghia',5,'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8','07-25-2003','Can Tho','minhnghia.service@gmail.com','0123456789','Off');
GO
ALTER TABLE "Employees" CHECK CONSTRAINT ALL
go
GO

-------------------------------------------
set quoted_identifier on
go
ALTER TABLE "Customers" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (6, 'Pham Hieu Tho', 'Can Tho', '0938973817', 'hieutho1510@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'Pham Hieu Tho', '1234567890123456', '2023-01-01', '2025-12-31', 123, 'credit card', 100, 'Gold');
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (7, 'Ly Minh Nghia', 'Can Tho', '0123456781', 'minhnghia.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Ly Minh Nghia', '2345678901234567', '2023-01-01', '2025-12-31', 456, 'credit card', 100, 'Gold');
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (8, 'Pham Huu Nhan', 'Can Tho', '0123456782', 'huunhan.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Pham Huu Nhan', '3456789012345678', '2023-01-01', '2025-12-31', 789, 'credit card', 100, 'Gold');
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (9, 'Nguyen Minhh Tuan', 'Can Tho', '0123436789', 'minhtuan.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Nguyen Minhh Tuan', '4567890123456789', '2023-01-01', '2025-12-31', 234, 'prepaid card', 100, 'Gold');
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (10, 'Nguyen Thi My Hao', 'Can Tho', '0123456789', 'myhao.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'Nguyen Thi My Hao', '5678901234567890', '2023-01-01', '2025-12-31', 567, 'prepaid card', 100, 'Gold');
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (11, 'Ong Kim Thanh', 'Can Tho', '0123456759', 'kimthanh.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Ong Kim Thanh', '6789012345678901', '2023-01-01', '2025-12-31', 890, 'prepaid card', 100, 'Gold');
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (12, 'Nguyen Trong Tri', 'Can Tho', '0123466789', 'trongtri.cus0@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Nguyen Trong Tri', '7890123456789012', '2023-01-01', '2025-12-31', 345, 'debit card', 100, 'Gold');
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (13, 'Nguyen Tan Phat', 'Can Tho', '0123457789', 'tanphat.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Nguyen Tan Phat', '8901234567890123', '2023-01-01', '2025-12-31', 678, 'debit card', 100, 'Gold');
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (14, 'Truong Ngoc Anh', 'Can Tho', '0123458789', 'ngocanh.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'Truong Ngoc Anh', '9012345678901234', '2023-01-01', '2025-12-31', 901, 'debit card', 100, 'Gold');
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (15, 'Nguyen Thi Anh Thu', 'Can Tho', '0923456789', 'anhthu.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'Nguyen Thi Anh Thu', '0123456789012345', '2023-01-01', '2025-12-31', 123, 'credit card', 100, 'Gold');
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (16, 'Pham Van Hien', 'Can Tho', '0123423789', 'vanhien.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Pham Van Hien', '1234567890123456', '2023-01-01', '2025-12-31', 456, 'credit card', 100, 'Gold');
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (17, 'To Ngoc Lan', 'Can Tho', '0123456119', 'ngoclan.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'To Ngoc Lan', '2345678901234567', '2023-01-01', '2025-12-31', 789, 'credit card', 100, 'Gold');
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (18, 'Truong Thi Ngoc Trang', 'Can Tho', '0123456789', 'ngoctrang.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'Truong Thi Ngoc Trang', '3456789012345678', '2023-01-01', '2025-12-31', 234, 'prepaid card', 100, 'Gold');
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (19, 'Nguyen Ngoc Hien', 'Can Tho', '0123453189', 'ngochien.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'Nguyen Ngoc Hien', '4567890123456789', '2023-01-01', '2025-12-31', 567, 'prepaid card', 100, 'Gold');
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (20, 'Nguyen Thanh Dat', 'Can Tho', '0125356789', 'thanhdat.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Nguyen Thanh Dat', '5678901234567890', '2023-01-01', '2025-12-31', 890, 'prepaid card', 100, 'Gold');
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (21, 'Dang Minh Dat', 'Can Tho', '0123453589', 'minhdat.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Dang Minh Dat', '6789012345678901', '2023-01-01', '2025-12-31', 345, 'debit card', 100, 'Gold');
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (22, 'Tran Thi Thu Thao', 'Can Tho', '0112456789', 'thuthao.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'Tran Thi Thu Thao', '7890123456789012', '2023-01-01', '2025-12-31', 678, 'debit card', 100, 'Gold');
INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (23, 'Vuong Nguyen Kim Tuyen', 'Can Tho', '0753456789', 'kimtuyen.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'Vuong Nguyen Kim Tuyen', '8901234567890123', '2023-01-01', '2025-12-31', 901, 'debit card', 100, 'Gold');

GO
ALTER TABLE "Customers" CHECK CONSTRAINT ALL
go
GO

-------------------------------------------
set quoted_identifier on
go
ALTER TABLE "Blogs" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Blogs" ( employeeID, title, content) VALUES 
(2, 'Pancake SanTa Beetroot Strawberry', 
'<p style="text-align: center">
        <img
          src="https://www.lottemart.vn/media/wysiwyg/pancake.jpg"
          alt="pancake"
          width="1200"
          height="1200"
        />
      </p>' +
'<p>
        <span >
          Pancake is a poetic dish originating from France, with many flavors
          and many different ways of preparation, so Pancake always has its own
          appeal. Today we will go into the kitchen and try to make this
          extremely unique Strawberry Beetroot Pancake. This will definitely be
          a dessert that will increase happiness in a cozy evening gathering
          with the family.
        </span>
      </p>' +
'<p style="text-align: center">
        <span >
          <img
            src="https://www.lottemart.vn/media/wysiwyg/pancake1.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>' +
'<p style="text-align: left">
        <span >&nbsp;</span>
      </p>
' +
'<p><strong>Ingredient:</strong></p>' +
'<ul>
        <li aria-level="1" >
          Strawberries: 20 fruits.
        </li>
        <li aria-level="1" >
          Pancake flour: 300 grams.
        </li>
        <li aria-level="1" >Milk: 2/3 Cup.</li>
        <li aria-level="1" >Butter: 1 tablespoon.</li>
        <li aria-level="1" >Beetroot: 120 grams.</li>
        <li aria-level="1" >Eggs: 1 egg.</li>
        <li aria-level="1" >Fresh Cream: 1/2 cup.</li>
      </ul>' +
'<p>
        <strong>Step 1:</strong>
        <span >
          Cut the beetroot into very small and thin slices, then mince them or
          put them in a blender to puree. Cut strawberries into rolls, then wash
          and drain.</span
        >
      </p>' +
'<p style="text-align: center">
        <span >
          <img
            src="https://www.lottemart.vn/media/wysiwyg/4e6bacfa-6ffb-49c0-bc61-ebdde354b99a.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>' +
'<p>
        <strong>Step 2:</strong>
        <span >
          Put the cake flour in a bowl, then add eggs, milk and beetroot puree
          and mix with a whisk.</span
        >
      </p>' +
'<p style="text-align: center">
        <span >
          <img
            src="https://www.lottemart.vn/media/wysiwyg/pancake3.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>' +
'<p>
        <strong>Step 3:</strong>
        <span >
          Put the butter in the hot pan, when the butter melts, add the smooth
          flour into the pan to the size you want. When the cake is done, take
          it out onto a plate.</span
        >
      </p>' +
' <p style="text-align: center">
        <span >
          <img
            src="https://www.lottemart.vn/media/wysiwyg/pancake4.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>' +
'<p>
        <strong>Step 4:</strong>
        <span >
          Put the cream and sugar in a bowl and gently whip the cream, prepare
          the pancakes in order: pancakes, then cream, add a layer of pancakes,
          and a layer of cream, on top of strawberries.</span
        >
      </p>' +
'<p style="text-align: center">
        <span >
          <img
            src="https://www.lottemart.vn/media/wysiwyg/pancake5.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>' +
'<p>
        <span >
          How to make Reindeer: Place marshmallows under the stick, pin
          chocolate squares to the stick, use hard Marshmallow for eyes and
          cookies for horns. Let''s shape the reindeer''s nose using red
          chocolate.</span
        >
      </p>' +
'<p style="text-align: center">
        <span >
          <img
            src="https://www.lottemart.vn/media/wysiwyg/pancake6.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>' +
'<p>
        <strong>Step 5:</strong>
        <span >
          So Pancake Santa is done, a little tip for decoration, you can add
          cream on top of the strawberries to make them look more like
          Santa.</span
        >
      </p>' +
'<p style="text-align: center">
        <span >
          <img
            src="https://www.lottemart.vn/media/wysiwyg/pancake7_1.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>');
	  ---------------------------------------------
INSERT INTO "Blogs" (employeeID, title, content) VALUES 
(4, 'Spicy Kim Chi Cold Noodles', 
'<p style="text-align: center">
        <img
          src="https://www.lottemart.vn/media/wysiwyg/milanh.jpg"
          alt="pancake"
          width="1200"
          height="1200"
        />
      </p>' +
'<p>
        <span >
          Noodles are a dish that is easy to crave, especially on rainy days and
          are also extremely easy to eat. That''s why noodles will be on the menu
          today, we will introduce to you Korean spicy Kim Chi noodles, with
          only a few basic ingredients but this noodle dish is still very
          special with its steaming flavor. its own guide.
        </span>
      </p>' +
'<p style="text-align: center">
        <span >
          <img
            src="https://www.lottemart.vn/media/wysiwyg/milanh1.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>' +
'<p style="text-align: left">
        <span >&nbsp;</span>
      </p>
' +
'<p><strong>Ingredient:</strong></p>' +
'<ul>
        <li aria-level="1" >
          Kimchi: a little.
        </li>
        <li aria-level="1" >
          Cucumber: 1/2 fruit
        </li>
        <li aria-level="1" >Sesame: 1 little</li>
        <li aria-level="1" >Vermicelli: 200 grams</li>
        <li aria-level="1" >Boiled eggs: 1 egg</li>
      </ul>' +
'<p><strong>Spice:</strong></p>' +
'<ul>
        <li aria-level="1" >
          Pepper: 2 spoons.
        </li>
        <li aria-level="1" >
          Oligo olive oil: 2 spoons.
        </li>
        <li aria-level="1" >Sugar: 1 tablespoon.</li>
        <li aria-level="1" >Sesame oil: 1 tablespoon.</li>
        <li aria-level="1" >Vinegar: 2 tablespoons.</li>
		<li aria-level="1" >Soy sauce: 1 tablespoon.</li>
		<li aria-level="1" >Pepper: a little.</li>
      </ul>'+
'<p>
        <strong>Step 1:</strong>
        <span >
          Cut Kim Chi, cut cucumber into pieces, mix with prepared spices. Cut
          boiled eggs in half.</span
        >
      </p>' +
'<p style="text-align: center">
        <span >
          <img
            src="https://www.lottemart.vn/media/wysiwyg/milanh2.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>' +
'<p>
        <strong>Step 2:</strong>
        <span >
          Put the noodles in the heated water and use chopsticks to stir so the
          noodles don''t stick together. When the water boils, add half a glass
          of cold water and continue to add water 3-4 times.</span
        >
      </p>' +
'<p style="text-align: center">
        <span >
          <img
            src="https://www.lottemart.vn/media/wysiwyg/milanh3.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>' +
'<p style="text-align: center">
        <span >
          <img
            src="https://www.lottemart.vn/media/wysiwyg/milanh4.jpg" 
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>' +
'<p>
        <strong>Step 3:</strong>
        <span >
          Take the noodles out, wash them to remove the boiled noodles, and add
          ice water to make the noodles more chewy.</span
        >
      </p>' +
' <p style="text-align: center">
        <span >
          <img
            src="https://www.lottemart.vn/media/wysiwyg/milanh5.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>' +
'<p>
        <strong>Step 4:</strong>
        <span >
          Put the noodles in a bowl, add the kimchi with sauce to the noodles
          and mix well.</span
        >
      </p>' +
'<p style="text-align: center">
        <span >
          <img
            src="https://www.lottemart.vn/media/wysiwyg/milanh6.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>' +
'<p>
        <strong>Step 5:</strong>
        <span >
          Add eggs, cucumber and sprinkle some sesame seeds. And let''s sip
          together.</span
        >
      </p>' +
'<p style="text-align: center">
        <span >
          <img
            src="https://www.lottemart.vn/media/wysiwyg/milanh7.jpg" 
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>'+
'<p style="text-align: center">
        <span >
          <img
            src="https://www.lottemart.vn/media/wysiwyg/milanh8.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>');

GO
ALTER TABLE "Blogs" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
ALTER TABLE "Feedbacks" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Feedbacks" (customerID, title, content, date, rate) VALUES (1,'Great Service!', 'I had a wonderful experience with your service.', '2023-01-15', 5);
INSERT INTO "Feedbacks" (customerID, title, content, date, rate) VALUES (2, 'Product Feedback', 'The product exceeded my expectations.', '2023-01-18', 4);
INSERT INTO "Feedbacks" (customerID, title, content, date, rate) VALUES (3, 'Improvement Suggestion', 'I think there are areas that could be improved.', '2023-01-20', 3);
INSERT INTO "Feedbacks" (customerID, title, content, date, rate) VALUES (4, 'Excellent Support', 'Your customer support team is fantastic!', '2023-01-22', 5);
INSERT INTO "Feedbacks" (customerID, title, content, date, rate) VALUES (5, 'Quality Issues', 'I encountered some quality issues with the product.', '2023-01-25', 2);
INSERT INTO "Feedbacks" (customerID, title, content, date, rate) VALUES (6, 'Prompt Delivery', 'The delivery was quick and on time.', '2023-01-28', 5);
INSERT INTO "Feedbacks" (customerID, title, content, date, rate) VALUES (7, 'Customer Service Feedback', 'I had a pleasant experience with your customer service.', '2023-02-01', 4);
INSERT INTO "Feedbacks" (customerID, title, content, date, rate) VALUES (8, 'Product Suggestions', 'Here are some suggestions for product enhancements.', '2023-02-05', 3);

GO
ALTER TABLE "Feedbacks" CHECK CONSTRAINT ALL
go
GO

------------------------------------
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
ALTER TABLE "ImportOrder" NOCHECK CONSTRAINT ALL
go
INSERT INTO dbo.ImportOrder (supplierID, categoryID, branchID, companyName, dateImport, hsCode, nameProduct, amountDelivery, address, city, phone, fax, postalCode, description, status, leadtime)
VALUES (1, 1, 1, 'ZipMart co.', '2023-01-15', 85166090, 'Kitchen infrared', 100, 'Ninh Kieu', 'Can Tho', '555-1111', '555-2222', '900000', 'Import of Kitchen infrared', 'Pending', 4);
INSERT INTO dbo.ImportOrder (supplierID, categoryID, branchID, companyName, dateImport, hsCode, nameProduct, amountDelivery, address, city, phone, fax, postalCode, description, status, leadtime)
VALUES (1, 2, 6, 'ZipMart co.', '2023-01-15', 73239390, 'Stainless Steel Pot', 30, 'Ninh Kieu', 'Can Tho', '555-4321', '555-2334', '900000', 'Import of Stainless Steel Pot', 'In Progress', 2);
INSERT INTO dbo.ImportOrder (supplierID, categoryID, branchID, companyName, dateImport, hsCode, nameProduct, amountDelivery, address, city, phone, fax, postalCode, description, status, leadtime)
VALUES (1, 3, 1, 'ZipMart co.', '2023-01-15', 85166090, 'oil-free fryer', 50, 'Ninh Kieu', 'Can Tho', '555-2123', '555-4442', '900000', 'Import of Kitchen infrared', 'Pending', 6);
INSERT INTO dbo.ImportOrder (supplierID, categoryID, branchID, companyName, dateImport, hsCode, nameProduct, amountDelivery, address, city, phone, fax, postalCode, description, status, leadtime)
VALUES (1, 1, 6, 'ZipMart co.', '2023-01-15', 70133700, 'Glass Bowl', 90, 'Ninh Kieu', 'Can Tho', '555-6123', '555-5213', '900000', 'Import of Glass Bowl', 'Completed', 4);

GO
ALTER TABLE "ImportOrder" CHECK CONSTRAINT ALL
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

------------------------------------
set quoted_identifier on
go
ALTER TABLE "Products" NOCHECK CONSTRAINT ALL
go
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (1, 1, 1,'Iron', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/homeelectric/zjaqhjmprv4hsyy0afcf',15.0000,50,150,'unit',
'<p>
	<span >Goldsun Gir2202 Dry Iron will be an indispensable device in the family, considered a modern iron, helping to iron clothes quickly, saving time and effort for the user.</span>
</p>' +
'<p>
	<span >Outstanding features: Anti-slip handle design. The table top is covered with high quality non-stick Teflon. Remove wrinkles from all fabrics. 3 levels of temperature adjustment. Automatically turns off power when not in use.</span>
</p>' +
'<p>
	<span >Technical specifications: Power: 1000W, 3 temperature adjustment levels, 3 ironing temperature levels. Surface covered with Teflon non-stick coating. Power source 220V~50HZ. Weight: 0.6kg</span>
</p>' + 
'<p>
	<span >Note when using: Low temperature for synthetic fibers/silk. Average temperature used for fabrics. High temperature for linen and cotton. Place of production: Vietnam.</span>
</p>', 200, 15, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (1, 1, 1,'Mini Gas Stove', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/homeelectric/gganbpkixbzpd4roex02',8.4300,50,150,'unit',
'<ul>
        <li aria-level="1" >
          <strong>NaMilux Mini gas stove NH-P3031PF</strong> is a convenient gas stove model with new improvements in extremely strong capacity, shortening cooking time and minimizing gas waste.
        </li>
        <li aria-level="1" >
          Use an automatic gas shut-off valve assembly to ensure safety when used.
        </li>
        <li aria-level="1" >The heat transfer plate design keeps the pressure in the gas can stable, giving a strong, blue fire throughout the cooking process.</li>
        <li aria-level="1" >Anti-slip tripod legs provide sturdy support, can be used for many sizes of pots and pans, and limit heat transfer to gas cans to avoid causing fires and explosions when in use.</li>
        <li aria-level="1" >The product uses eyelid rolling technology <i class="fa-thin fa-arrow-right"></i> creates sturdiness and easy cleaning.</li>
		<li aria-level="1" >Heat-resistant electroplated brackets (3500 degrees Celsius), bracket trays to prevent water from spilling into gas cans.</li>
</ul>', 200, 15, 0, 3);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (1, 1, 1,'Kitchen infrared', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/homeelectric/rp2yeycvq84roxf1pylx',17.5000,20,100,'unit',
'<p>
	<span >Sunhouse SHD6014 Infrared Stove is simply designed, super thin with a weight of 2kg, you can move it anywhere conveniently. The product is designed with two eye-catching main colors: black and white, the kitchen surface is made from a super durable material that is heat-resistant and durable, easy to observe.</span>
</p>' +
'<p>
	<span >Infrared stoves use modern technology, do not radiate heat, so they are very safe for users''health. The stove has a safety lock so you can rest assured when children come near. The super durable glass surface can withstand 800 degrees Celsius, prevents scratches and cracks at high temperatures, and the cooling fan helps radiate heat quickly, prevents short circuits, and increases the life of the stove.</span>
</p>' +
'<p>
	<span >Operating with a capacity of 2000W to cook food faster, smart timer function helps save energy and time</span>
</p>' + 
'<p>
	<span >Using electronic chips to control, many different cooking modes such as stir-frying, stewing, hot pot, frying,... make cooking easier and simpler.</span>
</p>', 100, 27, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (1, 2, 1,'Lock&Lock Grill EJG232 - Black', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/homeelectric/wzip6clrxi7wsccackrg',15.0000,50,150,'unit',
'<p>
	<span >Lock&Lock Grill EJG232 Black has a compact, simple but modern design, suitable for your kitchen space.</span>
</p>' +
'<p>
	<span >The product is used to grill food, with an effective non-stick grill surface, creating dishes with less oil, still retaining a delicious taste, ensuring the health of the user.</span>
</p>' +
'<p>
	<span >Product specifications: Dimensions: 560x330x85mm; Weight: 2kg/ including 2.8kg packaging.</span>
</p>' + 
'<p>
	<span >Notes when using: - Keep out of reach of children. Do not leave the product near places containing water or humid air, and avoid direct sunlight. - Avoid strong impacts, dropping the product and pay attention to electrical leakage incidents. - Only use the product for basic purposes, do not use it for other commercial purposes.</span>
</p>', 50, 0, 1, 2);
----------------------------------------
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (2, 3, 3,'Simply Pure 100% Soybean Oil Bottle', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/spice/qvf9obrq9ccj4caqmyev',0.5300,50,150,'unit',
'<p>
	<span >Simply Pure 100% Canola Oil 1L Bottle is produced from premium imported ingredients, retaining high levels of unsaturated fat (> 88%) to help protect heart health. The product also adds a rich source of vitamin A in sunflower oil that can prevent cataracts.</span>
</p>' +
'<p>
	<span >Simply Canola Oil is produced using modern refining technology from Europe, strictly complying with international standards on food safety and hygiene: FSSC 22000, HACCP and AIB. The product does not contain cholesterol and trans fatty acids, preservatives and colorants.</span>
</p>' +
'<p>
	<span >The product is conveniently bottled, the bottle is made from clean ingredients, free of impurities, ensuring consumer health.Simply is a cooking oil brand recommended by the Vietnam Heart Association.</span>
</p>' + 
'<p>
	<span >Instructions for use: Fry, stir-fry, mix salad, make cakes, cook vegetarian dishes. Store in a cool and dry place, away from direct sunlight. Close the lid tightly after use.</span>
</p>', 500, 0, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (2, 3, 5,'Cholimex Char Siu Sauce', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/spice/kq7cgznbxxf0sroshibb',2.2100,97,234,'unit',
'<p>
	<span >Cholimex Char Xiu Sauce 200G is packaged beautifully, neatly, and is easy to preserve. The product helps housewives save a lot of time when cooking.</span>
</p>' +
'<p>
	<span >The product does not contain toxic chemicals or preservatives, ensuring safety for consumers health.</span>
</p>' +
'<p>
	<span >Cholimex sauce is produced using modern, closed technology under strict supervision and inspection, helping to cook char siu meat faster and more convenient.</span>
</p>' , 434, 0, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (2, 3, 5,'Fuji Soy Sauce Chai', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/spice/l61kwh3uvpiupbjchumm',0.6900,100,543,'unit',
'<p>
	<span >Fuji Soy Sauce is extracted from soybeans, bringing a delicious, attractive taste to your family meals.</span>
</p>' +
'<p>
	<span >Manufactured using modern, advanced Japanese technology. Ingredients: Water, salt, naturally fermented soybean extract (kiage), vegetable protein,...Use directly or marinate meat, fish, braised dishes, stir-fry, and cook.</span>
</p>' +
'<p>
	<span >Storage: Dry, cool place, avoid direct sunlight, cover tightly after use. Made in Viet Nam.</span>
</p>' , 234, 5, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (2, 3, 5,'Chinsu Chili Sauce', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/spice/uksqho9qnyas1pktbglo',1.5600,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>NaMilux Mini gas stove NH-P3031PF</strong> Chili sauce is made from ripe red chili peppers with the strong aroma of garlic and subtle variations of spices to fill each chili sauce line of the Chinsu chili sauce brand.
        </li>
        <li aria-level="1" >
          Chinsu chili sauce 1kg large bottle is convenient and suitable for large families, helping to save money.
        </li>
</ul>', 223, 0, 1, 1);
--------------------------
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (3, 4, 9,'Tam Nong whole chicken', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/meat/qkeumjwmp5kbz6sjcqvd',0.2200,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>Tam Nong whole chicken 1.4kg is raised according to strict standards, under the supervision of experts.
        </li>
        <li aria-level="1" >Chickens are healthy, guaranteed not to be infected with flu or disease. Closed and safe livestock farming system. Raising livestock safely, using clean, high-quality food sources. Do not use weight gain substances or stimulants to help chickens grow bigger. The finished chicken processing factory system is clean, using modern technology, ensuring food hygiene and safety requirements.</li>
		<li aria-level="1" >Chicken is carefully packaged and preserved to deliver to consumers, keeping product quality always the best. Tam Nong chicken can be used to prepare many delicious dishes such as: boiled chicken, stewed chicken, grilled chicken, fried chicken, fried chicken.</li>
</ul>', 223, 0, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (3, 4, 9,'Imported beef shoulder core', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/meat/jerbgyghxitboo3tusdv',0.2300,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>The shoulder core is cut from the meat area between the shoulder and neck of the cow. In the middle of the tenderloin there is a thin, crispy and not tough tendon.
        </li>
        <li aria-level="1" >
          The meat is dark red in color, the shoulder core is moderately soft because it has an evenly distributed amount of fat and lean and is sweet and fragrant with a characteristic grain flavor.
        </li>
        <li aria-level="1" >Beef shoulder steak contains several essential nutrients including protein, iron, zinc, selenium, riboflavin, niacin, vitamin B6, vitamin B12, phosphorus, pantothenate, magnesium and potassium.</li>
</ul>', 223, 0, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (3, 5, 9,'CP chicken fillet', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/meat/gvwq6pm1ekdj7zcgzv6m',0.2400,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>CP Chicken Fillet 500g (ea) is made from clean chicken sources, raised in a farm system that meets strict standards.</li>
        <li aria-level="1" >
          The preliminary processing and preservation process is carried out in a closed process, ensuring food hygiene and safety.
        </li>
        <li aria-level="1" >Chicken is rich in vitamins A, B1, B2, C, E, acid, calcium, phosphorus, iron that the human body easily absorbs and digests. Good for the heart, fights depression, supports teeth and bones, promotes eye health.</li>
		<li aria-level="1" >The nutritional content of chicken breast is high and the amount of fat is low. Suitable for people who are in the process of losing weight and babies who are weaning.</li>
</ul>', 223, 0, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (3, 4, 9,'Pork Ribs', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/meat/plfutc8s2rzm0vwggqm5',0.2500,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>Pork ribs are a very popular cut of meat with soft meat, balanced with fat, keeping the meat from drying out. In particular, the cartilage is both soft and crunchy, bringing a pleasant feeling when eating.</li>
        <li aria-level="1" >
          Pork ribs are a good source of protein, providing essential amino acids for muscle growth and maintenance, as well as other body functions. Pork contains selenium, an important mineral that has antioxidant properties and supports the immune system.
        </li>
        <li aria-level="1" >Pork ribs are often used to cook many delicious and diverse dishes such as grilled, stir-fried, braised, steamed, cooked in soup, grilled, or processed into different dishes.</li>
		<li aria-level="1" >Pigs are raised using advanced technology and a closed meat collection process, ensuring food safety and hygiene. The product is packaged in a convenient, hygienic tray when it reaches the consumer.</li>
</ul>', 223, 0, 1, 1);
------------------------------
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (4, 4, 9,'Fresh Octopus', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/aquaticproducts/ua21gvgkeq5r07pwagju',0.2600,89,132,'unit',
'<p>
	<span >Fresh octopus meat provides essential vitamins for the body such as: A, B1, B2, PP, C and some other minerals such as calcium, phosphorus...</span>
</p>' +
'<p>
	<span >Octopus meat has many nutrients for body development such as zinc, copper, iron, iodine - very good for brain development.</span>
</p>' +
'<p>
	<span >Octopus meat has almost no fat and is very beneficial for muscles. This type of seafood is also rich in nutrients and has the effect of strengthening physical strength.</span>
</p>', 223, 0, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (4, 4, 9,'Tuna fillet', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/aquaticproducts/iirxqgqawhetde1ul0ff',0.2700,89,132,'unit',
'<p>
	<span >Ocean tuna is prepared from fresh ocean tuna, going through a careful selection process, closed processing and packaging process, ensuring all food hygiene and safety standards, safe for health.</span>
</p>' +
'<p>
	<span >Ocean tuna has high protein content, contains a lot of magnesium minerals, a mixture of B vitamins, potassium, a source of omega 3,... bringing great health benefits such as helping to: lose weight; eye health; prevent atherosclerosis; Activate brain cells and promote brain activities;....</span>
</p>' , 223, 0, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (4, 4, 9,'Saury', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/aquaticproducts/r6bm1uqumac3oxcqpygw',0.3000,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>Saury (sanma) caught mainly in September-October contains many healthy oils and is considered a famous specialty of Japanese autumn. During the Edo period (1603 - 1868), this shiny green-skinned fish was considered a low-grade food, while white fish were much more popular.</li>
        <li aria-level="1" >
          Fish contains a lot of fat and nutrients that are beneficial for health. Fish fat contains DHA and EPA, which are substances that have the ability to prevent cardiovascular diseases such as myocardial infarction and stroke. In addition, saury meat contains a lot of vitamins that are good for health such as Vitamin E, vitamin A, and vitamin D.
        </li>
        <li aria-level="1" >How to prepare saury: After buying saury, clean the fish belly. However, mackerel has the characteristic of having firm meat (a lot of meat), so the intestines are not much, making it very easy to clean. Soak the fish in diluted salt water, use a knife to gently scrape the fish skin to remove plaque. Then, cut the mackerel gills into bite-sized pieces or chop them to suit your cooking needs. Delicious dishes from saury: Mackerel in tomato sauce, fragrant braised mackerel, braised mackerel with tomatoes, grilled mackerel...</li>
		<li aria-level="1" >How to preserve fresh saury: Saury should be prepared immediately to maintain its freshness, but if you can use it in time, you can store it in the refrigerator.</li>
</ul>', 223, 0, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (4, 4, 9,'Red tilapia', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/aquaticproducts/oegoj0ht4xyaegewkxty',0.9800,89,132,'unit',
'<p>
	<span >Red tilapia or red tilapia, also known as red tilapia, is a freshwater fish belonging to the tilapia family. As a type of fish with delicious meat quality, red tilapia meat is white, clean, the meat fibers are firmly structured and especially the meat does not have too many bones.</span>
</p>' +
'<p>
	<span >Red tilapia is prepared from raw red tilapia, removing scales and internal organs, helping housewives save time in cleaning and easily prepare delicious dishes for the family quickly.</span>
</p>' +
'<p>
	<span >Red tilapia meat contains selenium content that helps enhance the function of white blood cells. Supports the thyroid gland and increases antioxidants to help prevent cancer, rheumatism and cardiovascular disease. Potassium reduces the risk of stroke, osteoporosis, kidney stones and high blood pressure. Selenium is a compound that helps reduce pain and prevent disease. In addition, red tilapia meat also contains vitamins, especially Vitamin A supports beauty and enhances vision.</span>
</p>', 223, 0, 1, 1);
-----------------------------
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (5, 5, 2,'Broccoli', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/vegetable/rlzdealvi9czewwxzmmx',0.5400,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>Broccoli (also known as broccoli) is a vegetable belonging to the cruciferous family with the scientific name Brassica Oleracea. Broccoli is a green vegetable shaped like a miniature tree, along with kale and cauliflower and are both cruciferous vegetables.</li>
        <li aria-level="1" >
          Broccoli is high in nutrients, including fiber, vitamin C, vitamin K, iron and potassium. Broccoli also contains more protein than most other vegetables.</li>
        <li aria-level="1" >The product does not contain harmful chemicals or growth stimulants, ensuring safety for consumers health. Therefore, you can completely feel secure when choosing this product for every meal your family has, making meals more delicious.</li>
</ul>', 223, 0, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (5, 5, 2,'Da Lat Beets', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/vegetable/tuezl9qfx8n2ddivmbmj',0.5600,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>Dalat Beetroot is a clean food, containing many nutrients such as fiber, vitamin A, potassium... good for the body. The active ingredients in fresh beets have the effect of nourishing the blood, strengthening the body immune components, helping to detect and eliminate abnormal cells before they can transform into cancer cells.</li>
        <li aria-level="1" >Products are grown using modern technology, ensuring freshness and food hygiene and safety.</li>
</ul>', 223, 0, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (5, 5, 2,'Iceberg Lettuce', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/vegetable/kxgdwbhlz8nkqxxmqupv',3.4300,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>Iceberg Lettuce (also known as head lettuce) is a type of lettuce originating from America. Ice Berg Lettuce is tightly rolled into large, heavy heads, similar to cabbage buds.</li>
        <li aria-level="1" >Lettuce is considered one of the healthiest vegetables, rich in vitamins C, K and anthocyanin polyphenols, which help prevent oxidation, reduce the risk of cardiovascular disease, diabetes and some cancers.</li>
		<li aria-level="1" >Thanks to being rich in antioxidants, regular use of the product also makes your skin softer and brighter.</li>
</ul>', 223, 0, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (5, 5, 2,'Da Lat Beef Tomatoes', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/vegetable/ovss5w0hae1ur16lfnad',2.3100,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>Beef tomatoes are large fruits, with an average fruit weight of 110-200 grams. The fruit is red, ripens early, and has a dense flesh. This type of tomato has won and won the American AAS (All-Amer Selections) award.</li>
        <li aria-level="1" >Beef tomatoes are thick, firm, have few seeds, and have few gaps when slicing. Beef tomatoes weigh more than regular tomatoes. Tomatoes are grown using modern technology, ensuring freshness and food hygiene and safety.</li>
</ul>', 223, 10, 1, 1);
---------------------------
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (6, 5, 4,'Happycook HEK-180PW 1.8L Electric Kettle', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/convenientproduct/vmu2xberquhpitiv82c9',5.9800,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>Compact design, light weight, a familiar household appliance in every Vietnamese family today. Capacity of 1.8 liters is suitable for families of 2 - 4 members or in offices, companies,...</li>
        <li aria-level="1" >
          The device is simply constructed and made from highly durable 201 stainless steel inside. The device will automatically shut off when it reaches a certain boiling point, ensuring anti-overheating and safety when boiling. The kettle shell is multi-layered with a hard, shiny plastic outer shell.</li>
        <li aria-level="1" >Large capacity heating plate: The Happy Cook kettle electric heating plate operates stably with a capacity of up to 1,500W, allowing water heating time to be shortened, meeting hot water needs in just a few minutes.</li>
</ul>', 223, 15, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (6, 5, 6,'Nano Cyclone Wet Towels 20 Pieces', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/convenientproduct/gd58smp2qn76g62yywla',4.3200,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>Nano Popular Premium Wet Towels 20 Pieces are soft, smooth wet towels and are a useful product for your life, helping you save time and shopping costs.</li>
        <li aria-level="1" >
          The product does not contain alcohol and does not cause skin sensitivity but has a gentle fragrance that creates a feeling of coolness, comfort and confidence.</li>
        <li aria-level="1" >The product is made from non-woven fabric, purified water, natural fragrances, and humectants that help keep and provide necessary moisture to the skin, keeping the skin fresh, clean, and not dry after wiping.</li>
</ul>', 223, 0, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (6, 5, 6,'Nestlé Milo Breakfast Cereal Box', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/convenientproduct/ue11htdtsyx36mevslwd',2.1100,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>Nestlé MILO breakfast cereal 300g box is extracted from barley germ and grains containing many minerals, iron, vitamins, calcium and iodized salt to give your baby abundant energy to start a new day.</li>
        <li aria-level="1" >
          The cake has the familiar Milo flavor, is crispy and fragrant and is loved by children. It is easy to eat and not boring. It can be used with milk to increase appetite and create an attractive breakfast.</li>
        <li aria-level="1" >The product is conveniently packaged, mothers can easily prepare it for their children to take to school or on family picnics. Ingredients: Cereal flour, oatmeal, rice flour, sugar, glucose syrup, palm oil, honey, minerals...</li>
</ul>', 223, 20, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (6, 5, 6,'Lipton Lemon Honey Lemon Tea Box', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/convenientproduct/eq6vow6fbwjld1uxbnhy',1.8900,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>Lipton Lemon Honey Lemon Tea Box of 16 Packs 12G is made from natural ingredients with a great combination of black tea leaves and natural lemon flavor to bring a refreshing feeling when enjoying.</li>
        <li aria-level="1" >It great to enjoy a cool cup of honey lemon tea after stressful working hours or in hot sunny weather. Lemon tea provides vitamin C to help strengthen the immune system, and the delicious lemon scent will bring a refreshing feeling full of vitality.</li>        <li aria-level="1" >The product is conveniently packaged, mothers can easily prepare it for their children to take to school or on family picnics. Ingredients: Cereal flour, oatmeal, rice flour, sugar, glucose syrup, palm oil, honey, minerals...</li>
        <li aria-level="1" >The product is produced using a closed technology to help preserve the full flavor of the leaves as well as the cool taste of lemon.</li>
</ul>', 223, 5, 1, 1);
--------------------------------------
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (7, 5, 8,'Boys Short-Sleeved T-shirt with 2-sided Print in Light Blue Size 11-14', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/fashion/onaqwtwwgcwalvjk7ku6',3.4200,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>Baby Boy T-shirt with 2-sided Print in Light Blue Size 11-14 is a simple short-sleeved, round-neck shirt, but the print on the front and back of the shirt makes your baby look very stylish, the fabric Breathable and absorbent cotton provides comfort when outdoors, helping your baby feel comfortable and confident when moving.</li>
        <li aria-level="1" >Mothers can combine the shirt with jeans, boy shorts, etc. with children sports shoes and sandals to give the baby a unique personality.</li>  
</ul>', 223, 5, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (7, 5, 8,'Automatic Folding Umbrella (Random Color Delivery)', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/fashion/quw4fgd6ladjwkndja97',0.21,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>Automatic Folding Umbrella (Random Color) has a sturdy, durable metal frame, durable and water-resistant umbrella material. The Automatic Folding Umbrella is designed to be compact and lightweight, easy to install quickly, and easy to carry with you.</li>
        <li aria-level="1" >The product has a 2-way automatic folding and opening design that is convenient for users. The product comes in many colors and is delivered randomly. Made in Viet Nam.</li>  
</ul>', 223, 0, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (7, 5, 8,'Women Onoff Collarless Socks with Star Floral Pattern 146-Sw01 (Random Color)', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/fashion/yfkm5mvmjqwigfumpue2',0.2100,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>Women OnOff Collarless Socks with Star Floral Pattern 146-SW01 use natural Cotton material with OEKO-Tex safety material certification, the socks are soft and sweat-absorbent, keeping feet dry and comfortable.</li>
        <li aria-level="1" >Y-shaped heel design keeps the socks close to the feet, soft elastic collar, light hug, antibacterial and deodorizing. Freesize socks; Colors include: white with gray collar, pink collar, white with pink collar, blue with gray collar, pink with gray collar, black delivered randomly.</li>  
        <li aria-level="1" >Care instructions: Wash with water below 40 degrees Celsius, do not dry at high temperatures, do not iron, do not use strong detergents. Made in Viet Nam.</li>  
</ul>', 223, 0, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (7, 14, 8,'Women Double Striped Elastic Shorts Size L (Random Color Delivery)', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/fashion/pxcgseqpstnsbit6fxmm',0.2100,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>Women double-striped elastic shorts use soft, smooth fish skin elastic material, with air holes created by overlapping layers of fish skin, giving the wearer a cool, sweat-absorbing feeling. Besides, the pants have meticulous seams, providing standard form, high durability as well as a dynamic and comfortable feeling for girls to wear.</li>
        <li aria-level="1" >The pants have a comfortable shorts design, an elastic waistband with a youthful and stylish drawstring, and double stripes on the side to create a personality and dynamism for girls to wear.</li>  
        <li aria-level="1" >Many fashionable, youthful colors, allowing women to easily mix & match with different types of shirts. Care instructions: Wash at normal temperature, do not use bleach, dry in the shade. Made in Viet Nam.</li>  
</ul>', 223, 0, 1, 1);
------------------------------
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (8, 5, 9,'Chinese Soft Red Pomegranate', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/fruit/aiy2mf6ot3ifpqiucara',0.2100,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>The Chinese soft red pomegranate is a fruit rich in antioxidants and has a delicious flavor, so it is very popular. It has red flesh, thick, succulent flesh, soft edible seeds, and a pink-yellow skin.</li>
        <li aria-level="1" >Pomegranate contains many nutrients, especially high fiber content that is good for the digestive system. Calcium, vitamin A, vitamin E and folic acid in pomegranates are very beneficial in strengthening the body resistance.</li>  
        <li aria-level="1" >Plant compounds in pomegranates have anti-inflammatory effects. Studies show that pomegranate extract can block enzymes that cause joint damage in people with osteoarthritis.</li>  
</ul>', 223, 0, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (8, 5, 9,'Cantaloupes of the Dutch variety', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/fruit/azcwxzlvjojyjq3e1fvz',5.3200,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>Dutch Cantaloupes are grown using Israeli technology with strict supervision, creating cantaloupe varieties with high sugar content, firm flesh, green flesh, round fruit, and longer-lasting sweetness.</li>
        <li aria-level="1" >Cantaloupe is a source of polyphenol antioxidants, which are beneficial for health in preventing cancer and strengthening the immune system.
Besides, cantaloupe contains a lot of fiber so it has a laxative effect and prevents constipation. Melon contains the largest amount of digestive enzymes among fresh fruits, more than papaya and mango. In addition, cantaloupe is also a rich source of beta-carotene, folic acid, potassium and vitamins C and A.</li>  
</ul>', 223, 20, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (8, 5, 7,'Cripps Red apples imported from South Africa', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/fruit/mzszzomdjvxmz84ymeyf',0.2100,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>South African Cripps Red apples are one of the imported South African apple varieties that are loved by many consumers with unique characteristics. Cripps Red apples are crunchy, sweet imported fruits with a mildly sour taste.</li>
        <li aria-level="1" >Apples contain many important nutrients, including fiber, vitamins, minerals and antioxidants. In addition, apples contain a lot of vitamin A, vitamin B, vitamin D, etc.</li>  
</ul>', 223, 0, 1, 1);
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, description, viewCount, discount, avaliable, inventoryStatus)
VALUES (8, 5, 7,'Guava variety from Taiwan', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/products/fruit/bxrmpdpzr2h8wlyqrzwu',0.2100,89,132,'unit',
'<ul>
        <li aria-level="1" >
          <strong>Taiwanese guava is also known as Taiwanese pear guava because the fruit is large like a pear. Taiwanese guavas have large, shiny fruits that are very crunchy, sweet and have very few seeds, so they are very popular.</li>
        <li aria-level="1" >This guava fruit contains many beneficial nutrients for the body such as Vitamin C and B vitamins that increase disinfection of the digestive system and are beneficial for the stomach and intestines.
Guava Pear can be eaten directly or made into juice to supplement nutrition for the body. You should especially eat guava peel because guava peel contains a lot of vitamin C, which is very good for beautifying the skin.</li>  
</ul>', 223, 0, 1, 1);

GO

ALTER TABLE "Products" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
ALTER TABLE "ThresholdAdjustment" NOCHECK CONSTRAINT ALL
go
INSERT INTO "ThresholdAdjustment" ( categoryID, reasonAdjustment, new_restockThreshold, dateAdjusted, statusThresholdAdjustments) VALUES (1, 'Low Inventory', 700, '2023-01-15 08:30:00', 'Accept');
INSERT INTO "ThresholdAdjustment" ( categoryID, reasonAdjustment, new_restockThreshold, dateAdjusted, statusThresholdAdjustments) VALUES (2, 'Excess Inventory', 500, '2023-02-20 10:45:00', 'Pending');
GO
ALTER TABLE "ThresholdAdjustment" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
ALTER TABLE "Orders" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (1, 1, '2023-01-15 08:30:00', '2023-01-18 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (2, 1, '2023-01-15 08:30:00', '2023-01-18 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (3, 2, '2023-01-16 09:30:00', '2023-01-18 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (4, 2, '2023-01-16 09:30:00', '2023-01-18 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
---------------------------------
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (5, 3, '2023-01-17 10:30:00', '2023-01-20 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Pending');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (6, 3, '2023-01-17 10:30:00', '2023-01-20 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Pending');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (7, 4, '2023-01-18 11:00:00', '2023-01-20 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Pending');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (8, 4, '2023-01-18 11:00:00', '2023-01-20 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (9, 2, '2023-01-19 11:00:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Pending');
------------------------------
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (10, 2, '2023-01-19 11:00:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (11, 1, '2023-01-20 15:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (11, 1, '2023-01-20 15:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Pending');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (12, 3, '2023-01-20 15:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (13, 3, '2023-01-20 18:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (14, 4, '2023-01-21 08:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (15, 4, '2023-01-21 08:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
------------------------------
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (16, 3, '2023-01-22 08:30:00', '2023-01-25 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (17, 3, '2023-01-22 15:30:00', '2023-01-25 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (17, 2, '2023-01-23 18:30:00', '2023-01-25 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (18, 2, '2023-01-24 08:30:00', '2023-01-30 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (1, 4, '2023-01-25 18:30:00', '2023-01-30 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
-----------------------------
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (2, 4, '2023-01-26 08:30:00', '2023-01-30 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (2, 1, '2023-01-27 15:30:00', '2023-01-30 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (3, 1, '2023-01-28 08:30:00', '2023-01-30 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (4, 1, '2023-01-29 18:30:00', '2023-02-03 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (2, 1, '2023-01-30 15:30:00', '2023-02-03 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');

GO
ALTER TABLE "Orders" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
ALTER TABLE "OrdersDetails" NOCHECK CONSTRAINT ALL
go
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (1, 1, 15.0000, 1, 15, 12.7500, 'credit card', 'Pham Hieu Tho', '1234567890123456');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (2, 2, 8.4300, 2, 15, 14.3300, 'credit card', 'Ly Minh Nghia', '2345678901234567');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (3, 3, 7.1500, 1, 27, 5.2200, 'credit card', 'Pham Huu Nhan', '3456789012345678');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (4, 4, 15.0000, 3, 0, 45.0000, 'prepaid card', 'Nguyen Minhh Tuan', '4567890123456789');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (5, 5, 0.5300, 1, 0, 0.5300, 'prepaid card', 'Nguyen Thi My Hao', '5678901234567890');
--------------------------
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (6, 6, 2.2100, 1, 0, 2.2100, 'prepaid card', 'Ong Kim Thanh', '6789012345678901');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (7, 7, 0.6900, 1, 0,  0.6900, 'debit card', 'Nguyen Trong Tri', '7890123456789012');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (8, 8, 1.5600, 3, 0, 4.6800, 'debit card', 'Nguyen Tan Phat', '8901234567890123');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (9, 9, 0.2200, 3, 0,  0.6600, 'debit card', 'Truong Ngoc Anh', '9012345678901234');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (10, 1, 15.0000, 1, 15, 12.7500, 'credit card', 'Nguyen Thi Anh Thu', '0123456789012345');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (11, 10, 0.2300, 1, 0, 0.2300, 'credit card', 'Pham Van Hien', '1234567890123456');
------------------------------
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (12, 1, 15.0000, 1, 15, 12.7500, 'credit card', 'Pham Van Hien', '1234567890123456');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (13, 1, 15.0000, 5, 15, 63.7500,  'credit card', 'To Ngoc Lan', '2345678901234567');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (14, 20, 2.3100, 1, 10, 2.0800, 'prepaid card', 'Truong Thi Ngoc Trang', '3456789012345678');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (15, 21, 2.3100, 1, 15, 1.9600, 'prepaid card', 'Nguyen Ngoc Hien', '4567890123456789');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (16, 21, 2.3100, 1, 15, 1.9600, 'prepaid card', 'Nguyen Thanh Dat', '5678901234567890');
------------------------------
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (17, 21, 2.3100, 1, 15, 2.0800, 'debit card', 'Dang Minh Dat', '6789012345678901');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (18, 22, 4.3200, 3, 0, 12.9600, 'debit card', 'Tran Thi Thu Thao', '7890123456789012');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (19, 23, 2.1100, 3, 25, 4.7500, 'debit card', 'Tran Thi Thu Thao', '7890123456789012');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (20, 24, 1.8900, 3, 5, 5.3900, 'debit card', 'Vuong Nguyen Kim Tuyen', '8901234567890123');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (21, 25, 3.4200, 3, 5, 9.7500, 'credit card', 'Pham Hieu Tho', '1234567890123456');
-----------------------------
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (22, 30, 5.3200, 1, 20, 4.2600, 'credit card', 'Ly Minh Nghia', '2345678901234567');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (23, 30, 5.3200, 1, 20, 4.2600, 'credit card', 'Ly Minh Nghia', '2345678901234567');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (24, 30, 5.3200, 1, 20, 4.2600, 'credit card', 'Pham Huu Nhan', '3456789012345678');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (25, 30, 5.3200, 1, 20, 4.2600, 'prepaid card', 'Nguyen Minhh Tuan', '4567890123456789');
INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (26, 30, 5.3200, 1, 20, 4.2600, 'prepaid card', 'Nguyen Thi My Hao', '5678901234567890');

GO
ALTER TABLE "OrdersDetails" CHECK CONSTRAINT ALL
go
GO

-----------------------------------

GO
USE [master]
GO
ALTER DATABASE [Cp2396g01_group5_db] SET  READ_WRITE 
GO
