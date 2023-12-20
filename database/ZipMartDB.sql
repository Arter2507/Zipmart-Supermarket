-- Enable the NOCOUNT option to prevent the return of the count of affected rows by DML statements.
SET NOCOUNT ON;
GO
GO
-- Use the 'master' database to perform system management operations.
USE [master];
GO
GO
-- Check IF the 'ZipMart' database exists, and drop it IF it does.
IF EXISTS (SELECT * FROM sys.databases WHERE name='ZipMart')
BEGIN
    DROP DATABASE [ZipMart];
END
GO
GO
-- Get the directory path of the master data file to use for the new 'ZipMart' data and log files.
DECLARE @device_directory NVARCHAR(520);
SELECT @device_directory = SUBSTRING(physical_name, 1, CHARINDEX(N'master.mdf', LOWER(physical_name)) - 1)
FROM master.sys.master_files WHERE database_id = 1 AND file_id = 1;

-- Create the 'ZipMart' database with primary data file and log file locations.
EXECUTE ('CREATE DATABASE ZipMart
  ON PRIMARY (NAME = N''ZipMart'', FILENAME = N''' + @device_directory + N'zmart.mdf'')
  LOG ON (NAME = N''ZipMart_log'',  FILENAME = N''' + @device_directory + N'zmart.ldf'')');
GO
GO

-- Set options for the 'ZipMart' database depending on the SQL Server version.
IF CAST(SERVERPROPERTY('ProductMajorVersion') AS INT) < 12 
BEGIN
  -- For versions earlier than SQL Server 2014
  ALTER DATABASE [ZipMart] SET RECOVERY SIMPLE WITH NO_WAIT;
  ALTER DATABASE [ZipMart] SET AUTO_UPDATE_STATISTICS_ASYNC OFF;
END
ELSE 
BEGIN
  -- For SQL Server 2014 and later
  ALTER DATABASE [ZipMart] SET RECOVERY SIMPLE WITH NO_WAIT;
  ALTER DATABASE [ZipMart] SET AUTO_UPDATE_STATISTICS_ASYNC OFF;
END
GO
GO

-- Set quoted identIFy for database
SET quoted_identIFier ON
GO

-- Set the date format to month/day/year.
SET DATEFORMAT mdy;
GO

-- Set the compatibility level for the 'ZipMart' database.
ALTER DATABASE [ZipMart] SET COMPATIBILITY_LEVEL = 150;
GO
GO
-- IF Full-Text Indexing is installed, enable it for the 'ZipMart' database.
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
BEGIN
  EXEC [ZipMart].[dbo].[sp_fulltext_database] @action = 'enable';
END
GO
GO

-- Set ANSI and ARITHABORT options for the 'ZipMart' database.
ALTER DATABASE [ZipMart] SET ANSI_NULL_DEFAULT OFF;
ALTER DATABASE [ZipMart] SET ANSI_NULLS OFF;
ALTER DATABASE [ZipMart] SET ANSI_PADDING OFF;
ALTER DATABASE [ZipMart] SET ANSI_WARNINGS OFF;
ALTER DATABASE [ZipMart] SET ARITHABORT OFF;
GO
GO

-- Set various options for the 'ZipMart' database.
-- (ALTER DATABASE statements are alphabetically ordered for clarity)
ALTER DATABASE [ZipMart] SET ALLOW_SNAPSHOT_ISOLATION OFF;
ALTER DATABASE [ZipMart] SET AUTO_CLOSE OFF;
ALTER DATABASE [ZipMart] SET AUTO_SHRINK OFF;
ALTER DATABASE [ZipMart] SET AUTO_UPDATE_STATISTICS ON;
ALTER DATABASE [ZipMart] SET DATE_CORRELATION_OPTIMIZATION OFF;
ALTER DATABASE [ZipMart] SET DB_CHAINING OFF;
ALTER DATABASE [ZipMart] SET DELAYED_DURABILITY = DISABLED;
ALTER DATABASE [ZipMart] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF );
ALTER DATABASE [ZipMart] SET HONOR_BROKER_PRIORITY OFF;
ALTER DATABASE [ZipMart] SET MULTI_USER;
ALTER DATABASE [ZipMart] SET NUMERIC_ROUNDABORT OFF;
ALTER DATABASE [ZipMart] SET PAGE_VERIFY CHECKSUM;
ALTER DATABASE [ZipMart] SET PARAMETERIZATION SIMPLE;
ALTER DATABASE [ZipMart] SET QUOTED_IDENTIFIER OFF;
ALTER DATABASE [ZipMart] SET QUERY_STORE = OFF;
ALTER DATABASE [ZipMart] SET READ_COMMITTED_SNAPSHOT OFF;
ALTER DATABASE [ZipMart] SET RECURSIVE_TRIGGERS OFF;
ALTER DATABASE [ZipMart] SET TARGET_RECOVERY_TIME = 60 SECONDS;
ALTER DATABASE [ZipMart] SET TRUSTWORTHY OFF;
GO
GO

-- Switch to the 'ZipMart' database to perform subsequent commands.
USE [ZipMart];
GO
GO

IF exists (select * from sysobjects where id = object_id('dbo.Sales by Year') and sysstat & 0xf = 4)
	drop procedure [dbo].[Sales by Year]
GO
IF exists (select * from sysobjects where id = object_id('dbo.UpdateTotalProductQuantities') and sysstat & 0xf = 4)
	drop procedure [dbo].[UpdateTotalProductQuantities]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Ten Most Expensive Products') and sysstat & 0xf = 4)
	drop procedure [dbo].[Ten Most Expensive Products]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Ten Best Seller Products') and sysstat & 0xf = 4)
	drop procedure [dbo].[Ten Best Seller Products]
GO
IF exists (select * from sysobjects where id = object_id('dbo.OrderByQuarter') and sysstat & 0xf = 2)
	drop view [dbo].[OrderByQuarter]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Sales Totals by Amount') and sysstat & 0xf = 2)
	drop view [dbo].[Sales Totals by Amount]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Summary of Sales by Quarter') and sysstat & 0xf = 2)
	drop view [dbo].[Summary of Sales by Quarter]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Summary of Sales by Year') and sysstat & 0xf = 2)
	drop view [dbo].[Summary of Sales by Year]
GO
IF exists (select * from sysobjects where id = object_id('dbo.InvoiceView') and sysstat & 0xf = 2)
	drop view [dbo].[InvoiceView]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Order Subtotals') and sysstat & 0xf = 2)
	drop view [dbo].[Order Subtotals]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Orders Qry') and sysstat & 0xf = 2)
	drop view [dbo].[Orders Qry]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Products Above Average Price') and sysstat & 0xf = 2)
	drop view [dbo].[Products Above Average Price]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Products by Category') and sysstat & 0xf = 2)
	drop view [dbo].[Products by Category]
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
IF exists (select * from sysobjects where id = object_id('dbo.Import') and sysstat & 0xf = 3)
	drop table [dbo].[Import]
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
IF exists (select * from sysobjects where id = object_id('dbo.CreditCard') and sysstat & 0xf = 3)
	drop table [dbo].[CreditCard]
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
IF exists (select * from sysobjects where id = object_id('dbo.Warehouse') and sysstat & 0xf = 3)
	drop table [dbo].[Warehouse]
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
	[permissionID] [int] IDENTITY(1,1) NOT NULL,
	[permissionName] [nvarchar](50) NULL,
	CONSTRAINT "PK_Permission" PRIMARY KEY  CLUSTERED 
	(
		"permissionID"ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]

GO
 CREATE  INDEX "PermissionName" ON "dbo"."Permission"("permissionName") 
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table CreditCard--
CREATE TABLE [dbo].[CreditCard](
	[cardID] [int] IDENTITY(1,1) NOT NULL,
	[cardName] [nvarchar](255) NULL,
	[cardNumber] [varchar](16) NULL,
	[valueFrom] [datetime] NULL,
	[expirationDate] [dateTime] NULL,
	[cvvNumber] [int] NULL,
	[cardType] [nvarchar](20) NULL,
	CONSTRAINT "PK_CreditCard" PRIMARY KEY CLUSTERED
	(
	"cardID"ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
	CONSTRAINT "CK_cvvNumber" CHECK (LEN(cvvNumber) = 3 AND cvvNumber LIKE '[0-9][0-9][0-9]'),
	CONSTRAINT [CK_CardNumberFormat] CHECK (LEN(cardNumber) = 16 AND cardNumber LIKE '[0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9]')
)ON [Primary]

GO 
 CREATE INDEX "CardNumber" ON "dbo"."CreditCard"("cardNumber")
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Account--
CREATE TABLE [dbo].[Accounts](
	[accountID] [int] IDENTITY(1,1) NOT NULL,
	[userName] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[hashedPassword] [VARBINARY](MAX),
	[permissionID] [int] NULL,
	[description] [nvarchar](255) NULL,
	CONSTRAINT "PK_Accounts" PRIMARY KEY  CLUSTERED 
	(
		"accountID"ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Accounts_Permission" FOREIGN KEY 
	(
		"permissionID"
	) REFERENCES "dbo"."Permission" (
		"permissionID"
	),
)ON [Primary]

GO
 CREATE INDEX "Username" ON "dbo"."Accounts"("userName")
GO
 CREATE INDEX "Password" ON "dbo"."Accounts"("password")
GO
 CREATE INDEX "PermissionID" ON "dbo"."Accounts"("permissionID")
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Manager--
CREATE TABLE [dbo].[Managers](
	[managerID] [int] IDENTITY(1,1) NOT NULL,
	[accountID] [int] NOT NULL,
	[fullname] [nvarchar](50) NULL,
	[address] [nvarchar](255) NULL,
	[phone] [nvarchar](50) NULL,
	[email] [nvarchar](255) NULL,
	CONSTRAINT "PK_Managers" PRIMARY KEY  CLUSTERED 
	(
		"managerID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Manager_Accounts" FOREIGN KEY 
	(
		"accountID"
	) REFERENCES "dbo"."Accounts" (
		"accountID"
	),
)ON [Primary]

GO
 CREATE INDEX "FullName" ON "dbo"."Managers"("fullname")
GO
 CREATE INDEX "Address" ON "dbo"."Managers"("address")
GO
 CREATE INDEX "Email" ON "dbo"."Managers"("email")
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Employee--
CREATE TABLE [dbo].[Employees](
	[employeeID] [int] IDENTITY(1,1) NOT NULL,
	[accountID] [int] NOT NULL,
	[fullname] [nvarchar](50) NULL,
	[address] [nvarchar](255) NULL,
	[phone] [nvarchar](50) NULL,
	[email] [nvarchar](255) NULL,
	[birthDate] [datetime] NULL ,
	[Notes] [nvarchar](max) NULL,
	imageURL [nvarchar](255) NULL,

	CONSTRAINT "PK_Employee" PRIMARY KEY CLUSTERED
	(
	"employeeID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Employee_Account" FOREIGN KEY 
	(
	"accountID"
	) REFERENCES "dbo"."Accounts"
	(
	"accountID"
	),
	CONSTRAINT "CK_Birthdate" CHECK (birthDate < getdate())
)ON [Primary]

GO
 CREATE INDEX "FullName" ON "dbo"."Employees"("fullname")
GO
 CREATE INDEX "Address" ON "dbo"."Employees"("address")
GO
 CREATE INDEX "Email" ON "dbo"."Employees"("email")
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Customers--
CREATE TABLE [dbo].[Customers](
	[customerID] [int] IDENTITY(1,1) NOT NULL,
	[cardID] [int] NOT NULL,
	[accountID] [int] NULL,
	[fullname] [nvarchar](255) NULL,
	[address] [nvarchar](50) NULL,
	[phone] [nvarchar](50) NULL,
	[email] [nvarchar](255) NULL,
	[birthDate] [datetime] NULL ,
	[imageURL] [nvarchar](255) NULL,
	[cardName] [nvarchar](255)NULL,
	[cardNumber] [varchar](16)NULL,
	[point] [bigint] NULL,
	[rank] [nvarchar](50) NULL,
	CONSTRAINT "PK_Customer" PRIMARY KEY CLUSTERED
	(
	"customerID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Customer_CreditCard" FOREIGN KEY 
	(
	"cardID"
	) REFERENCES "dbo"."CreditCard"
	(
	"cardID"
	),
	CONSTRAINT "FK_Customer_Account" FOREIGN KEY 
	(
	"accountID"
	) REFERENCES "dbo"."Accounts"
	(
	"accountID"
	),
	CONSTRAINT "CK_Birthdate_Customer" CHECK (birthDate < getdate())
)ON [Primary]

GO
 CREATE INDEX "FullName" ON "dbo"."Customers"("fullname")
GO
 CREATE INDEX "Address" ON "dbo"."Customers"("address")
GO
 CREATE INDEX "Email" ON "dbo"."Customers"("email")
GO
 CREATE INDEX "Rank" ON "dbo"."Customers"("rank")
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table blogFB--
CREATE TABLE [dbo].[BlogFeedBacks](
	[feedbackID] [int] IDENTITY(1,1) NOT NULL,
	[customerID] [int] NOT NULL,
	[title] [nvarchar](50) NULL,
	[content] [nvarchar](max) NULL,
	[date] [datetime] NULL,
	CONSTRAINT "PK_BlogFeedbacks" PRIMARY KEY  CLUSTERED 
	(
		"feedbackID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_BlogFeedBack_Customer" FOREIGN KEY 
	(
		"customerID"
	) REFERENCES "dbo"."Customers" (
		"customerID"
	),
)ON [Primary]

GO
 CREATE INDEX "Title" ON "dbo"."blogFeedBacks"("title")
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Blogs--
CREATE TABLE [dbo].[Blogs](
	[blogID] [int] IDENTITY(1,1) NOT NULL,
	[employeeID] [int] NOT NULL,
	[title] [nvarchar](255) NULL,
	[imageURL] [nvarchar](255) NULL,
	[content] [nvarchar](max) NULL,
	[viewCount] [int] NULL,
	CONSTRAINT "PK_Feedback" PRIMARY KEY  CLUSTERED 
	(
		"blogID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Blog_Employee" FOREIGN KEY 
	(
		"employeeID"
	) REFERENCES "dbo"."Employees" (
		"employeeID"
	),
)ON [Primary]

GO
 CREATE INDEX "Title_Blog" ON "dbo"."Blogs"("title")
GO
 CREATE INDEX "ViewCount" ON "dbo"."Blogs"("viewCount")
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table FeedBacks--
CREATE TABLE [dbo].[Feedbacks](
	[feedbackID] [int] IDENTITY(1,1) NOT NULL,
	[customerID] [int] NOT NULL,
	[title] [nvarchar](255) NOT NULL,
	[content] [nvarchar](max) NULL,
	[date] [datetime] NULL,
	[rate] [int] NULL,
	CONSTRAINT "PK_Feedback_Cus" PRIMARY KEY  CLUSTERED 
	(
		"feedbackID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_FeedBack_Customer" FOREIGN KEY 
	(
		"customerID"
	) REFERENCES "dbo"."Customers" (
		"customerID"
	),
)ON [Primary]

GO
 CREATE INDEX "Title_Feedback" ON "dbo"."Feedbacks"("title")
GO
 CREATE INDEX "Rate" ON "dbo"."Feedbacks"("rate")
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Suppliers--
CREATE TABLE [dbo].[Suppliers](
	[supplierID] [int] IDENTITY(1,1) NOT NULL,
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
	CONSTRAINT "PK_Supplier" PRIMARY KEY CLUSTERED
	(
	"supplierID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]

GO
 CREATE  INDEX "CompanyName" ON "dbo"."Suppliers"("companyName")
GO
 CREATE  INDEX "ContactName" ON "dbo"."Suppliers"("contactName")
GO
 CREATE  INDEX "Address" ON "dbo"."Suppliers"("address")
GO
 CREATE  INDEX "PostalCode" ON "dbo"."Suppliers"("postalCode")
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Brand--
CREATE TABLE [dbo].[Brand](
	[brandID] [int] IDENTITY(1,1) NOT NULL,
	[supplierID] [int] NOT NULL,
	[brandName] [nvarchar](255) NOT NULL,
	[address] [nvarchar](255) NULL,
	CONSTRAINT "PK_Brand" PRIMARY KEY CLUSTERED
	(
	"brandID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
	CONSTRAINT "FK_Brand_Supplier" FOREIGN KEY 
	(
		"supplierID"
	) REFERENCES "dbo"."Suppliers" (
		"supplierID"
	),
)ON [Primary]

GO
 CREATE INDEX "BrandName" ON "dbo"."Brand"("brandName")
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Warehouse--
CREATE TABLE [dbo].[Warehouse](
	[warehouseID] [int] IDENTITY(1,1) NOT NULL,
	[companyName] [nvarchar] (40) NULL,
	[warehouseName] [nvarchar](50)  NULL,
	[address] [nvarchar](255) NULL,
	[city] [nvarchar](15) NULL,
	[phone] [nvarchar](24) NULL,
	[fax] [nvarchar](24) NULL,
	[postalCode] [nvarchar](10) NULL,
	[description] [nvarchar](255) NULL,
	CONSTRAINT "PK_Warehouse" PRIMARY KEY CLUSTERED
	(
	"warehouseID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]

GO
 CREATE INDEX "CompanyName" ON "dbo"."Warehouse"("companyName")
GO
 CREATE INDEX "WarehouseName" ON "dbo"."Warehouse"("warehouseName")
GO
CREATE INDEX "Address" ON "dbo"."Warehouse"("address")
GO
 CREATE INDEX "PostalCode" ON "dbo"."Warehouse"("postalCode")
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Branch--
CREATE TABLE [dbo].[Branch](
	[branchID] [int] IDENTITY(1,1) NOT NULL,
	[companyName] [nvarchar] (40) NULL,
	[branchName] [nvarchar](50)  NULL,
	[address] [nvarchar](255) NULL,
	[city] [nvarchar](15) NULL,
	[phone] [nvarchar](24) NULL,
	[fax] [nvarchar](24) NULL,
	[postalCode] [nvarchar](10) NULL,
	[description] [nvarchar](255) NULL,
CONSTRAINT "PK_Branch" PRIMARY KEY CLUSTERED
	(
	"branchID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]

GO
 CREATE INDEX "CompanyName" ON "dbo"."Branch"("companyName")
GO
 CREATE INDEX "BranchName" ON "dbo"."Branch"("branchName")
GO
CREATE INDEX "Address" ON "dbo"."Branch"("address")
GO
 CREATE INDEX "PostalCode" ON "dbo"."Branch"("postalCode")
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Categories--
CREATE TABLE [dbo].[Categories](
	[categoryID] [int] IDENTITY(1,1) NOT NULL,
	[categoryName] [nvarchar](50) NULL,
	[restockThreshold] [int] NULL,
	[description] [nvarchar](255) NULL,
	CONSTRAINT "PK_Category" PRIMARY KEY CLUSTERED
	(
	"categoryID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]

GO
 CREATE INDEX "CategoryName" ON "dbo"."Categories"("categoryName")
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Import--
	CREATE TABLE [dbo].[Import](
		[importID] [int] IDENTITY(1,1) NOT NULL,
		[supplierID] [int] NOT NULL,
		[brandID] [int] NOT NULL,
		[branchID] [int] NOT NULL,
		[categoryID] [int] NOT NULL,
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
		CONSTRAINT "PK_Import" PRIMARY KEY CLUSTERED
		(
		"importID" ASC
		)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

		CONSTRAINT "FK_Import_Supplier" FOREIGN KEY 
		(
			"supplierID"
		) REFERENCES "dbo"."Suppliers" (
			"supplierID"
		),

		CONSTRAINT "FK_Import_Brand" FOREIGN KEY 
		(
			"brandID"
		) REFERENCES "dbo"."Brand" (
			"brandID"
		),

		CONSTRAINT "FK_Import_Branch" FOREIGN KEY 
		(
			"branchID"
		) REFERENCES "dbo"."Branch" (
			"branchID"
		),

		CONSTRAINT "FK_Import_Category" FOREIGN KEY 
		(
			"categoryID"
		) REFERENCES "dbo"."Categories" (
			"categoryID"
		),

	)ON [Primary]

GO
 CREATE INDEX "Branch" ON "dbo"."Import"("branchID")
GO
 CREATE INDEX "CompanyName" ON "dbo"."Import"("companyName")
GO
CREATE INDEX "Address" ON "dbo"."Import"("address")
GO
 CREATE INDEX "PostalCode" ON "dbo"."Import"("postalCode")
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Inventory Status--
CREATE TABLE InventoryStatus
(
    statusID INT identity(1,1) NOT NULL,
    statusName NVARCHAR(50) NULL
	CONSTRAINT "PK_Status" PRIMARY KEY CLUSTERED
	(
	"statusID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

)ON [Primary]
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Products--
CREATE TABLE [dbo].[Products](
	[productID] [int] IDENTITY(1,1) NOT NULL,
	[warehouseID] [int] NOT NULL,
	[categoryID] [int] NOT NULL,
	[brandID] [int] NOT NULL,
	[supplierID] [int] NOT NULL,
	[productName] [nvarchar](255) NULL,
	[imageURL] [nvarchar](255) NULL,
	[unitPrice] [money] NULL CONSTRAINT "DF_Products_UnitPrice" DEFAULT (0),
	[quantity] [int] NULL,
	[quantitySold] [int] NULL,
	[quantityInStock] [int] NULL,
	[unit] [nvarchar](50) NULL,
	[newAdjustment] [int]NULL CONSTRAINT "DF_Products_AdjustmentThreshold" DEFAULT (0),
	[description] [nvarchar](max) NULL,
	[viewCount] [int] NULL,
	[discount] [int] NULL,
	[avaliable] [int] NOT NULL,		
	CONSTRAINT "PK_Products" PRIMARY KEY  CLUSTERED 
	(
		"productID"
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Products_Warehouse" FOREIGN KEY 
	(
		"warehouseID"
	) REFERENCES "dbo"."Warehouse" (
		"warehouseID"
	),
	CONSTRAINT "FK_Products_Categories" FOREIGN KEY 
	(
		"categoryID"
	) REFERENCES "dbo"."Categories" (
		"categoryID"
	),
	CONSTRAINT "FK_Products_Brands" FOREIGN KEY 
	(
		"brandID"
	) REFERENCES "dbo"."Brand" (
		"brandID"
	),
	CONSTRAINT "FK_Products_Suppliers" FOREIGN KEY 
	(
		"supplierID"
	) REFERENCES "dbo"."Suppliers" (
		"supplierID"
	),
	CONSTRAINT "FK_Products_InventoryStatus" FOREIGN KEY 
	(
		"avaliable"
	) REFERENCES "dbo"."InventoryStatus" (
		"statusID"
	),
	CONSTRAINT "CK_Products_UnitPrice" CHECK (unitPrice >= 0),
	CONSTRAINT "CK_QuantityPro" CHECK (quantity <= quantityInStock AND quantitySold <= quantity)

)On [Primary]

GO
 CREATE  INDEX "CategoriesProducts" ON "dbo"."Products"("categoryID")
GO
 CREATE  INDEX "CategoryID" ON "dbo"."Products"("categoryID")
GO
 CREATE  INDEX "BrandProducts" ON "dbo"."Products"("brandID")
GO
 CREATE  INDEX "Brand" ON "dbo"."Products"("brandID")
GO
 CREATE  INDEX "ProductName" ON "dbo"."Products"("productName")
GO
 CREATE  INDEX "SupplierID" ON "dbo"."Products"("supplierID")
GO
 CREATE  INDEX "SuppliersProducts" ON "dbo"."Products"("supplierID")
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table ThAdj--
CREATE TABLE [dbo].[ThresholdAdjustment](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[productID] [int] NOT NULL,
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
		"productID"
	) REFERENCES "dbo"."Products" (
		"productID"
	),
)On [Primary]

GO
 CREATE  INDEX "Status" ON "dbo"."ThresholdAdjustment"("statusThresholdAdjustments")
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Orders--
CREATE TABLE dbo.Orders (
    [orderID] [INT] IDENTITY(1,1) NOT NULL,
    [customerID] [INT] NOT NULL,
	[employeeID] [INT] NOT NULL,
    [orderDate] [DATETIME] NULL,
    [shipDate] [DATETIME] NULL,
	[shipAddress] [nvarchar](255) NULL,
	[note] [nvarchar](max) NULL,
    [status] [NVARCHAR](50) NULL,
	CONSTRAINT "PK_Orders" PRIMARY KEY CLUSTERED 
	(
	orderID ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

    CONSTRAINT "FK_Orders_Customers" FOREIGN KEY ("customerID") REFERENCES "dbo"."Customers"("CustomerID"),
	CONSTRAINT "FK_Orders_Employees" FOREIGN KEY ("employeeID") REFERENCES "dbo"."Employees"("employeeID")
)ON [Primary]

GO
 CREATE INDEX "CustomerID" ON "dbo"."Orders"("customerID")
GO
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table OrderDetails
CREATE TABLE [dbo].[OrdersDetails](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[orderID] [int]NOT NULL,
	[productID][int]NOT NULL,
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
		"orderID"
	),
	CONSTRAINT "FK_Order_Details_Pro" FOREIGN KEY 
	(
		"productID"
	) REFERENCES "dbo"."Products" (
		"productID"
	),
	CONSTRAINT "CK_Discount" CHECK (Discount >= 0 and (Discount <= 50)),
	CONSTRAINT "CK_Quantity" CHECK (Quantity > 0),
)

GO
 CREATE  INDEX "OrderID" ON "dbo"."OrdersDetails"("orderID")
GO
 CREATE  INDEX "OrdersOrder_Details" ON "dbo"."OrdersDetails"("orderID")
GO
 CREATE  INDEX "ProID" ON "dbo"."OrdersDetails"("productID")
GO
 CREATE  INDEX "CartOrder_Details" ON "dbo"."OrdersDetails"("productID")
GO
GO


create view "Orders Qry" AS
SELECT Orders.OrderID, Orders.CustomerID, Orders.EmployeeID, Orders.OrderDate, Orders.ShipDate, 
	Orders.ShipAddress, Orders.note, Orders.status,  
	Customers.fullname, Customers.Address, Customers.phone, Customers.email,
	CreditCard.cardName, CreditCard.cardNumber,CreditCard.cvvNumber,CreditCard.valueFrom,CreditCard.expirationDate,
	Customers.point,
	customers.rank
FROM Customers INNER JOIN Orders ON Customers.CustomerID = Orders.CustomerID 
INNER JOIN CreditCard ON Customers.cardID = CreditCard.cardID
GO
GO

create view "Products Above Average Price" AS
SELECT Products.ProductName, Products.UnitPrice
FROM Products
WHERE Products.UnitPrice>(SELECT AVG(UnitPrice) From Products)
--ORDER BY Products.UnitPrice DESC
GO
GO

create view "Products by Category" AS
SELECT Categories.CategoryName, Products.ProductName, Products.quantity, Products.quantityInStock, Products.quantitySold, Categories.restockThreshold
FROM Categories INNER JOIN Products ON Categories.CategoryID = Products.CategoryID
--ORDER BY Categories.CategoryName, Products.ProductName
GO
GO

CREATE VIEW OrderByQuarter AS
SELECT
    OrderID,
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
    CardID,
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
            Orders.OrderID,
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
            Customers.CardID,
            Customers.Point,
            Customers.Rank
        FROM
            Customers
        INNER JOIN Orders ON Customers.CustomerID = Orders.CustomerID
        INNER JOIN CreditCard ON Customers.CardID = CreditCard.CardID
    ) AS Subquery;

GO
GO

CREATE VIEW InvoiceView AS
SELECT
    O.orderID,
    O.customerID,
    O.employeeID,
    O.orderDate,
    O.shipDate,
    O.shipAddress,
    O.note,
    O.status,
    OD.productID,
	OD.unitPrice,
    OD.quantity,
    OD.discount,
    OD.totalPrice,
    OD.paymentMethod,
	C.cardName,
	C.cardNumber
FROM
    dbo.Orders AS O
JOIN
    dbo.OrdersDetails AS OD ON O.orderID = OD.orderID
INNER JOIN Customers AS C ON O.customerID = C.customerID;
GO
GO

CREATE VIEW "Order Subtotals" AS
SELECT
    OD.OrderID,
    SUM(CONVERT(money, (P.unitPrice * OD.quantity * (1 - OD.Discount) / 100)) * 100) AS Subtotal
FROM
    dbo.OrdersDetails AS OD
JOIN
    dbo.Products AS P ON P.productID = OD.productID
GROUP BY
    OD.OrderID;
GO
GO

CREATE VIEW "Sales Totals by Amount" AS
SELECT 
    SUM("Order Subtotals".Subtotal) AS SaleAmount, 
    Orders.OrderID, 
    Customers.fullname, 
    Orders.ShipDate,
    DATEPART(QUARTER, Orders.ShipDate) AS Quarter
FROM 
    Customers 
    INNER JOIN Orders ON Customers.CustomerID = Orders.CustomerID
    INNER JOIN "Order Subtotals" ON Orders.OrderID = "Order Subtotals".OrderID
WHERE 
    ("Order Subtotals".Subtotal > 2500)
GROUP BY 
    Orders.OrderID, 
    Customers.fullname, 
    Orders.ShipDate, 
    DATEPART(QUARTER, Orders.ShipDate);
GO
GO

create view "Summary of Sales by Quarter" AS
SELECT Orders.ShipDate, Orders.OrderID, "Order Subtotals".Subtotal
FROM Orders INNER JOIN "Order Subtotals" ON Orders.OrderID = "Order Subtotals".OrderID
WHERE Orders.ShipDate IS NOT NULL
--ORDER BY Orders.ShippedDate
GO
GO

create view "Summary of Sales by Year" AS
SELECT Orders.ShipDate, Orders.OrderID, "Order Subtotals".Subtotal
FROM Orders INNER JOIN "Order Subtotals" ON Orders.OrderID = "Order Subtotals".OrderID
WHERE Orders.ShipDate IS NOT NULL
--ORDER BY Orders.ShippedDate
GO
GO

create procedure "Ten Most Expensive Products" AS
SET ROWCOUNT 10
SELECT Products.ProductName AS TenMostExpensiveProducts, Products.UnitPrice
FROM Products
ORDER BY Products.UnitPrice DESC
GO
GO

CREATE PROCEDURE "TenBestSellerProducts"
AS
BEGIN
    SET ROWCOUNT 10;

    SELECT TOP 10
        Products.ProductName AS TenBestSellerProducts,
        Products.quantity
    FROM
        Products
    ORDER BY
        Products.quantity DESC;
END;
GO
GO

create procedure "Sales by Year" 
	@Beginning_Date DateTime, @Ending_Date DateTime AS
SELECT Orders.ShipDate, Orders.OrderID, "Order Subtotals".Subtotal, DATENAME(yy,ShipDate) AS Year
FROM Orders INNER JOIN "Order Subtotals" ON Orders.OrderID = "Order Subtotals".OrderID
WHERE Orders.ShipDate Between @Beginning_Date And @Ending_Date
GO
GO

CREATE PROCEDURE UpdateTotalProductQuantities
AS
BEGIN
    UPDATE P
    SET P.quantity = 
        CASE 
            WHEN (P.quantity + P.quantityInStock) > C.restockThreshold THEN C.restockThreshold - P.quantityInStock
            ELSE CASE WHEN P.quantity > P.quantityInStock THEN P.quantityInStock ELSE P.quantity END
        END
    FROM dbo.Products P
    INNER JOIN dbo.Categories C ON P.categoryID = C.categoryID;
END
GO
GO

--=======================INSERT DATA=====================--
set quoted_identifier on
go
set identity_insert "Permission" on
go
ALTER TABLE "Permission" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Permission" ("permissionID","permissionName") VALUES (1,'manager');
INSERT INTO "Permission" ("permissionID","permissionName") VALUES (2,'employee');
INSERT INTO "Permission" ("permissionID","permissionName") VALUES (3,'customer');
GO
set identity_insert "Permission" off
go
ALTER TABLE "Permission" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
set identity_insert "CreditCard" on
go
ALTER TABLE "CreditCard" NOCHECK CONSTRAINT ALL
go
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (1,'Pham Hieu Tho', '1234567890123456', '2023-01-01', '2025-12-31', 123, 'credit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (2,'Ly Minh Nghia', '2345678901234567', '2023-01-01', '2025-12-31', 456, 'credit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (3,'Pham Huu Nhan', '3456789012345678', '2023-01-01', '2025-12-31', 789, 'credit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (4,'Nguyen Minhh Tuan', '4567890123456789', '2023-01-01', '2025-12-31', 234, 'prepaid card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (5,'Nguyen Thi My Hao', '5678901234567890', '2023-01-01', '2025-12-31', 567, 'prepaid card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (6,'Ong Kim Thanh', '6789012345678901', '2023-01-01', '2025-12-31', 890, 'prepaid card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (7,'Nguyen Trong Tri', '7890123456789012', '2023-01-01', '2025-12-31', 345, 'debit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (8,'Nguyen Tan Phat', '8901234567890123', '2023-01-01', '2025-12-31', 678, 'debit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (9,'Truong Ngoc Anh', '9012345678901234', '2023-01-01', '2025-12-31', 901, 'debit card');
    -- Add more rows as needed
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (10,'Nguyen Thi Anh Thu', '0123456789012345', '2023-01-01', '2025-12-31', 123, 'credit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (11,'Pham Van Hien', '1234567890123456', '2023-01-01', '2025-12-31', 456, 'credit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (12,'To Ngoc Lan', '2345678901234567', '2023-01-01', '2025-12-31', 789, 'credit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (13,'Truong Thi Ngoc Trang', '3456789012345678', '2023-01-01', '2025-12-31', 234, 'prepaid card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (14,'Nguyen Ngoc Hien', '4567890123456789', '2023-01-01', '2025-12-31', 567, 'prepaid card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (15,'Nguyen Thanh Dat', '5678901234567890', '2023-01-01', '2025-12-31', 890, 'prepaid card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (16,'Dang Minh Dat', '6789012345678901', '2023-01-01', '2025-12-31', 345, 'debit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (17,'Tran Thi Thu Thao', '7890123456789012', '2023-01-01', '2025-12-31', 678, 'debit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (18,'Vuong Nguyen Kim Tuyen', '8901234567890123', '2023-01-01', '2025-12-31', 901, 'debit card');

GO
set identity_insert "CreditCard" off
go
ALTER TABLE "CreditCard" CHECK CONSTRAINT ALL
go
GO

-------------------------------------------
set quoted_identifier on
go
set identity_insert "Accounts" on
go
ALTER TABLE "Accounts" NOCHECK CONSTRAINT ALL
go
---User with permission 1-----
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword","permissionID", "description") VALUES (1,'Admin', 'Admin', HASHBYTES('SHA2_256', 'Admin'),1, 'Manager');
---User with permission 2-----
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (2,'quangtuong', 'Admin', HASHBYTES('SHA2_256', 'Admin'), 2, 'Employee');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (3,'hieutho', 'Admin', HASHBYTES('SHA2_256', 'Admin'), 2, 'Employee');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (4,'huunhan', 'Admin', HASHBYTES('SHA2_256', 'Admin'), 2, 'Employee');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (5,'minhnghia', 'Admin', HASHBYTES('SHA2_256', 'Admin'), 2, 'Employee');
---User with permission 3-----
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (6,'hieutho', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (7,'huunhan', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (8,'minhnghia', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (9,'minhtuan', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (10,'myhao', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (11,'kimthanh', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (12,'trongtri', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (13,'tanphat', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (14,'ngocanh', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (15,'anhthu', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (16,'vanhien', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (17,'ngoclan', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (18,'ngoctrang', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (19,'ngochien', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (20,'thanhdat', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (22,'minhdat', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (23,'thuthao', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (24,'kimtuyen', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
GO
set identity_insert "Accounts" off
go
ALTER TABLE "Accounts" CHECK CONSTRAINT ALL
go
GO

-------------------------------------------
set quoted_identifier on
go
set identity_insert "Managers" on
go
ALTER TABLE "Managers" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Managers" ("managerID","fullname","accountID","address","email","phone") VALUES (1,'Manager',1,'Can Tho','manager.zipmart@gamil.com',' 0292 3731 072');
GO
set identity_insert "Managers" off
go
ALTER TABLE "Managers" CHECK CONSTRAINT ALL
go
GO

-------------------------------------------
set quoted_identifier on
go
set identity_insert "Employees" on
go
ALTER TABLE "Employees" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Employees" ("employeeID","fullname","accountID","imageURL","birthDate","address","email","phone","Notes") VALUES (1,'To Quang Tuong',2,'img\uploads\emp\man.jpg','07-25-2003','Can Tho','qtuong.257@gmail.com','0917895327','My note');
INSERT INTO "Employees" ("employeeID","fullname","accountID","imageURL","birthDate","address","email","phone","Notes") VALUES (2,'Pham Hieu Tho',2,'img\uploads\emp\woman.jpg','07-04-2003','Can Tho','hieutho1510@gmail.com','0938973817','My note');
INSERT INTO "Employees" ("employeeID","fullname","accountID","imageURL","birthDate","address","email","phone","Notes") VALUES (3,'Pham Huu Nhan',2,'img\uploads\emp\man.jpg','07-25-2003','Can Tho','huunhan.service@gmail.com','0123456789','Off');
INSERT INTO "Employees" ("employeeID","fullname","accountID","imageURL","birthDate","address","email","phone","Notes") VALUES (4,'Ly Minh Nghia',2,'img\uploads\emp\man.jpg','07-25-2003','Can Tho','minhnghia.service@gmail.com','0123456789','Off');
GO
set identity_insert "Employees" off
go
ALTER TABLE "Employees" CHECK CONSTRAINT ALL
go
GO

-------------------------------------------
set quoted_identifier on
go
set identity_insert "Customers" on
go
ALTER TABLE "Customers" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (1, 1, 6, 'Pham Hieu Tho', 'Can Tho', '0938973817', 'hieutho1510@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'Pham Hieu Tho', '1234567890123456', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (2, 2, 7, 'Ly Minh Nghia', 'Can Tho', '0123456781', 'minhnghia.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'Ly Minh Nghia', '2345678901234567', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (3, 3, 8, 'Pham Huu Nhan', 'Can Tho', '0123456782', 'huunhan.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'Pham Huu Nhan', '3456789012345678', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (4, 4, 9, 'Nguyen Minhh Tuan', 'Can Tho', '0123436789', 'minhtuan.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'Nguyen Minhh Tuan', '4567890123456789', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (5, 5, 10, 'Nguyen Thi My Hao', 'Can Tho', '0123456789', 'myhao.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'Nguyen Thi My Hao', '5678901234567890', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (6, 6, 11, 'Ong Kim Thanh', 'Can Tho', '0123456759', 'kimthanh.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'Ong Kim Thanh', '6789012345678901', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (7, 7, 12, 'Nguyen Trong Tri', 'Can Tho', '0123466789', 'trongtri.cus0@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'Nguyen Trong Tri', '7890123456789012', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (8, 8, 13, 'Nguyen Tan Phat', 'Can Tho', '0123457789', 'tanphat.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'Nguyen Tan Phat', '8901234567890123', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (9, 9, 14, 'Truong Ngoc Anh', 'Can Tho', '0123458789', 'ngocanh.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'Truong Ngoc Anh', '9012345678901234', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (10, 10, 15, 'Nguyen Thi Anh Thu', 'Can Tho', '0923456789', 'anhthu.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'Nguyen Thi Anh Thu', '0123456789012345', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (11, 11, 16, 'Pham Van Hien', 'Can Tho', '0123423789', 'vanhien.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'Pham Van Hien', '1234567890123456', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (12, 12, 17, 'To Ngoc Lan', 'Can Tho', '0123456119', 'ngoclan.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'To Ngoc Lan', '2345678901234567', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (13, 13, 18, 'Truong Thi Ngoc Trang', 'Can Tho', '0123456789', 'ngoctrang.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'Truong Thi Ngoc Trang', '3456789012345678', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (14, 14, 19, 'Nguyen Ngoc Hien', 'Can Tho', '0123453189', 'ngochien.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'Nguyen Ngoc Hien', '4567890123456789', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (15, 15, 20, 'Nguyen Thanh Dat', 'Can Tho', '0125356789', 'thanhdat.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'Nguyen Thanh Dat', '5678901234567890', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (16, 16, 21, 'Dang Minh Dat', 'Can Tho', '0123453589', 'minhdat.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'Dang Minh Dat', '6789012345678901', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (17, 17, 22, 'Tran Thi Thu Thao', 'Can Tho', '0112456789', 'thuthao.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'Tran Thi Thu Thao', '7890123456789012', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","point", "rank"	) VALUES (18, 18, 23, 'Vuong Nguyen Kim Tuyen', 'Can Tho', '0753456789', 'kimtuyen.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 'Vuong Nguyen Kim Tuyen', '8901234567890123', 100, 'Gold');

GO
set identity_insert "Customers" off
go
ALTER TABLE "Customers" CHECK CONSTRAINT ALL
go
GO

-------------------------------------------
set quoted_identifier on
go
set identity_insert "BlogFeedBacks" on
go
ALTER TABLE "BlogFeedBacks" NOCHECK CONSTRAINT ALL
go
INSERT INTO "BlogFeedBacks" (feedbackID, customerID, title, content, date) VALUES (1, 1, 'Great Experience', 'I had a great experience with your blog!', '2023-01-05 10:30:00');
INSERT INTO "BlogFeedBacks" (feedbackID, customerID, title, content, date) VALUES (2, 2, 'Quality Service', 'Your service is top-notch. Keep up the good work!', '2023-01-08 14:45:00');
INSERT INTO "BlogFeedBacks" (feedbackID, customerID, title, content, date) VALUES (3, 3, 'Suggestions', 'I have some suggestions for improvement. Can we discuss them?', '2023-01-12 18:20:00');
INSERT INTO "BlogFeedBacks" (feedbackID, customerID, title, content, date) VALUES (4, 4, 'Happy Customer', 'I am a happy customer. Your blog exceeded my expectations.', '2023-01-15 09:55:00');
INSERT INTO "BlogFeedBacks" (feedbackID, customerID, title, content, date) VALUES (5, 2, 'Great Blog', 'This is a great blog. Thanks you very much!', '2023-01-20 10:30:00');

GO
set identity_insert "BlogFeedBacks" off
go
ALTER TABLE "BlogFeedBacks" CHECK CONSTRAINT ALL
go
GO

-------------------------------------------
set quoted_identifier on
go
set identity_insert "Blogs" on
go
ALTER TABLE "Blogs" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Blogs" (blogID, employeeID, title, content, viewCount) VALUES 
(1, 2, 'Pancake SanTa Beetroot Strawberry', 
'<p style="text-align: center">
        <img
          src="https://www.lottemart.vn/media/wysiwyg/pancake.jpg"
          alt="pancake"
          width="1200"
          height="1200"
        />
      </p>' +
'<p>
        <span style="font-weight: 400">
          Pancake is a poetic dish originating from France, with many flavors
          and many different ways of preparation, so Pancake always has its own
          appeal. Today we will go into the kitchen and try to make this
          extremely unique Strawberry Beetroot Pancake. This will definitely be
          a dessert that will increase happiness in a cozy evening gathering
          with the family.
        </span>
      </p>' +
'<p style="text-align: center">
        <span style="font-weight: 400">
          <img
            src="https://www.lottemart.vn/media/wysiwyg/pancake1.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>' +
'<p style="text-align: left">
        <span style="font-weight: 400">&nbsp;</span>
      </p>
' +
'<p><strong>Ingredient:</strong></p>' +
'<ul>
        <li aria-level="1" style="font-weight: 400">
          Strawberries: 20 fruits.
        </li>
        <li aria-level="1" style="font-weight: 400">
          Pancake flour: 300 grams.
        </li>
        <li aria-level="1" style="font-weight: 400">Milk: 2/3 Cup.</li>
        <li aria-level="1" style="font-weight: 400">Butter: 1 tablespoon.</li>
        <li aria-level="1" style="font-weight: 400">Beetroot: 120 grams.</li>
        <li aria-level="1" style="font-weight: 400">Eggs: 1 egg.</li>
        <li aria-level="1" style="font-weight: 400">Fresh Cream: 1/2 cup.</li>
      </ul>' +
'<p>
        <strong>Step 1:</strong>
        <span style="font-weight: 400">
          Cut the beetroot into very small and thin slices, then mince them or
          put them in a blender to puree. Cut strawberries into rolls, then wash
          and drain.</span
        >
      </p>' +
'<p style="text-align: center">
        <span style="font-weight: 400">
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
        <span style="font-weight: 400">
          Put the cake flour in a bowl, then add eggs, milk and beetroot puree
          and mix with a whisk.</span
        >
      </p>' +
'<p style="text-align: center">
        <span style="font-weight: 400">
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
        <span style="font-weight: 400">
          Put the butter in the hot pan, when the butter melts, add the smooth
          flour into the pan to the size you want. When the cake is done, take
          it out onto a plate.</span
        >
      </p>' +
' <p style="text-align: center">
        <span style="font-weight: 400">
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
        <span style="font-weight: 400">
          Put the cream and sugar in a bowl and gently whip the cream, prepare
          the pancakes in order: pancakes, then cream, add a layer of pancakes,
          and a layer of cream, on top of strawberries.</span
        >
      </p>' +
'<p style="text-align: center">
        <span style="font-weight: 400">
          <img
            src="https://www.lottemart.vn/media/wysiwyg/pancake5.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>' +
'<p>
        <span style="font-weight: 400">
          How to make Reindeer: Place marshmallows under the stick, pin
          chocolate squares to the stick, use hard Marshmallow for eyes and
          cookies for horns. Let''s shape the reindeer''s nose using red
          chocolate.</span
        >
      </p>' +
'<p style="text-align: center">
        <span style="font-weight: 400">
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
        <span style="font-weight: 400">
          So Pancake Santa is done, a little tip for decoration, you can add
          cream on top of the strawberries to make them look more like
          Santa.</span
        >
      </p>' +
'<p style="text-align: center">
        <span style="font-weight: 400">
          <img
            src="https://www.lottemart.vn/media/wysiwyg/pancake7_1.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>', 750);
	  ---------------------------------------------
INSERT INTO "Blogs" (blogID, employeeID, title, content, viewCount) VALUES 
(2, 4, 'Spicy Kim Chi Cold Noodles', 
'<p style="text-align: center">
        <img
          src="https://www.lottemart.vn/media/wysiwyg/milanh.jpg"
          alt="pancake"
          width="1200"
          height="1200"
        />
      </p>' +
'<p>
        <span style="font-weight: 400">
          Noodles are a dish that is easy to crave, especially on rainy days and
          are also extremely easy to eat. That''s why noodles will be on the menu
          today, we will introduce to you Korean spicy Kim Chi noodles, with
          only a few basic ingredients but this noodle dish is still very
          special with its steaming flavor. its own guide.
        </span>
      </p>' +
'<p style="text-align: center">
        <span style="font-weight: 400">
          <img
            src="https://www.lottemart.vn/media/wysiwyg/milanh1.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>' +
'<p style="text-align: left">
        <span style="font-weight: 400">&nbsp;</span>
      </p>
' +
'<p><strong>Ingredient:</strong></p>' +
'<ul>
        <li aria-level="1" style="font-weight: 400">
          Kimchi: a little.
        </li>
        <li aria-level="1" style="font-weight: 400">
          Cucumber: 1/2 fruit
        </li>
        <li aria-level="1" style="font-weight: 400">Sesame: 1 little</li>
        <li aria-level="1" style="font-weight: 400">Vermicelli: 200 grams</li>
        <li aria-level="1" style="font-weight: 400">Boiled eggs: 1 egg</li>
      </ul>' +
'<p><strong>Spice:</strong></p>' +
'<ul>
        <li aria-level="1" style="font-weight: 400">
          Pepper: 2 spoons.
        </li>
        <li aria-level="1" style="font-weight: 400">
          Oligo olive oil: 2 spoons.
        </li>
        <li aria-level="1" style="font-weight: 400">Sugar: 1 tablespoon.</li>
        <li aria-level="1" style="font-weight: 400">Sesame oil: 1 tablespoon.</li>
        <li aria-level="1" style="font-weight: 400">Vinegar: 2 tablespoons.</li>
		<li aria-level="1" style="font-weight: 400">Soy sauce: 1 tablespoon.</li>
		<li aria-level="1" style="font-weight: 400">Pepper: a little.</li>
      </ul>'+
'<p>
        <strong>Step 1:</strong>
        <span style="font-weight: 400">
          Cut Kim Chi, cut cucumber into pieces, mix with prepared spices. Cut
          boiled eggs in half.</span
        >
      </p>' +
'<p style="text-align: center">
        <span style="font-weight: 400">
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
        <span style="font-weight: 400">
          Put the noodles in the heated water and use chopsticks to stir so the
          noodles don''t stick together. When the water boils, add half a glass
          of cold water and continue to add water 3-4 times.</span
        >
      </p>' +
'<p style="text-align: center">
        <span style="font-weight: 400">
          <img
            src="https://www.lottemart.vn/media/wysiwyg/milanh3.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>' +
'<p style="text-align: center">
        <span style="font-weight: 400">
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
        <span style="font-weight: 400">
          Take the noodles out, wash them to remove the boiled noodles, and add
          ice water to make the noodles more chewy.</span
        >
      </p>' +
' <p style="text-align: center">
        <span style="font-weight: 400">
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
        <span style="font-weight: 400">
          Put the noodles in a bowl, add the kimchi with sauce to the noodles
          and mix well.</span
        >
      </p>' +
'<p style="text-align: center">
        <span style="font-weight: 400">
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
        <span style="font-weight: 400">
          Add eggs, cucumber and sprinkle some sesame seeds. And let''s sip
          together.</span
        >
      </p>' +
'<p style="text-align: center">
        <span style="font-weight: 400">
          <img
            src="https://www.lottemart.vn/media/wysiwyg/milanh7.jpg" 
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>'+
'<p style="text-align: center">
        <span style="font-weight: 400">
          <img
            src="https://www.lottemart.vn/media/wysiwyg/milanh8.jpg"
            alt="pancake"
            width="1200"
            height="1200"
          />
        </span>
      </p>', 750);

GO
set identity_insert "Blogs" off
go
ALTER TABLE "Blogs" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
set identity_insert "Feedbacks" on
go
ALTER TABLE "Feedbacks" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Feedbacks" (feedbackID,customerID, title, content, date, rate) VALUES (1, 1,'Great Service!', 'I had a wonderful experience with your service.', '2023-01-15', 5);
INSERT INTO "Feedbacks" (feedbackID,customerID, title, content, date, rate) VALUES (2, 2, 'Product Feedback', 'The product exceeded my expectations.', '2023-01-18', 4);
INSERT INTO "Feedbacks" (feedbackID,customerID, title, content, date, rate) VALUES (3, 3, 'Improvement Suggestion', 'I think there are areas that could be improved.', '2023-01-20', 3);
INSERT INTO "Feedbacks" (feedbackID,customerID, title, content, date, rate) VALUES (4, 4, 'Excellent Support', 'Your customer support team is fantastic!', '2023-01-22', 5);
INSERT INTO "Feedbacks" (feedbackID,customerID, title, content, date, rate) VALUES (5, 5, 'Quality Issues', 'I encountered some quality issues with the product.', '2023-01-25', 2);
INSERT INTO "Feedbacks" (feedbackID,customerID, title, content, date, rate) VALUES (6, 6, 'Prompt Delivery', 'The delivery was quick and on time.', '2023-01-28', 5);
INSERT INTO "Feedbacks" (feedbackID,customerID, title, content, date, rate) VALUES (7, 7, 'Customer Service Feedback', 'I had a pleasant experience with your customer service.', '2023-02-01', 4);
INSERT INTO "Feedbacks" (feedbackID,customerID, title, content, date, rate) VALUES (8, 8, 'Product Suggestions', 'Here are some suggestions for product enhancements.', '2023-02-05', 3);

GO
set identity_insert "Feedbacks" off
go
ALTER TABLE "Feedbacks" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
set identity_insert "Suppliers" on
go
ALTER TABLE "Suppliers" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Suppliers" (supplierID , companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES (1, 'SupplierA', 'John Doe', 'Manager', 'Ninh Kieu', 'Can Tho', '900000', '555-1234', '555-5678', 'http://www.supplierA.com', 'Supplier Description');
INSERT INTO "Suppliers" (supplierID , companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES (2, 'SupplierB', 'Jane Smith', 'Sales Representative', 'Binh Thuy', 'Can Tho', '900000', '67890', '555-9876', 'http://www.supplierB.com', 'Supplier Description');
INSERT INTO "Suppliers" (supplierID , companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES (3, 'SupplierC', 'Bob Johnson', 'Owner', 'Thot Not', 'Can Tho', '900000', '555-1111', '555-2222', 'http://www.supplierC.com', 'Supplier Description');
INSERT INTO "Suppliers" (supplierID , companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES (4, 'SupplierD', 'Alice Johnson', 'Sales Manager', 'Cai Rang', 'Can Tho', '900000', '555-3333', '555-4444', 'http://www.supplierD.com', 'Supplier Description');
INSERT INTO "Suppliers" (supplierID , companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES (5, 'SupplierE', 'Charlie Brown', 'Marketing Coordinator', 'O Mon', 'Can Tho', '900000', '555-5555', '555-6666', 'http://www.supplierE.com', 'Supplier Description');
INSERT INTO "Suppliers" (supplierID , companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES (6, 'SupplierF', 'Eva Green', 'Customer Service Representative', 'Co Do', 'Can Tho', '900000', '555-7777', '555-8888', 'http://www.supplierF.com', 'Supplier Description');
INSERT INTO "Suppliers" (supplierID , companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES (7, 'SupplierG', 'David White', 'Technical Support', 'Phong Dien', 'Can Tho', '900000', '555-9999', '555-0000', 'http://www.supplierG.com', 'Supplier Description');
INSERT INTO "Suppliers" (supplierID , companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES (8, 'SupplierH', 'Grace Miller', 'Operations Manager', 'Thoi Lai', 'Can Tho', '900000', '555-1234', '555-5678', 'http://www.supplierH.com', 'Supplier Description');
INSERT INTO "Suppliers" (supplierID , companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES (9, 'SupplierI', 'Jane Doe', 'Product Manager', 'Vinh Thanh', 'Vinh Thanh', '900000', '555-7583', '555-2347', 'http://www.supplierI.com', 'Supplier Description');

GO
set identity_insert "Suppliers" off
go
ALTER TABLE "Suppliers" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
set identity_insert "Brand" on
go
ALTER TABLE "Brand" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Brand" (brandID, supplierID ,brandName, address) VALUES (1, 1, 'BrandA', 'Can Tho');
INSERT INTO "Brand" (brandID, supplierID ,brandName, address) VALUES (2, 1, 'BrandB', 'Can Tho');
INSERT INTO "Brand" (brandID, supplierID ,brandName, address) VALUES (3, 3, 'BrandC', 'Can Tho');
INSERT INTO "Brand" (brandID, supplierID ,brandName, address) VALUES (4, 5, 'BrandD', 'Can Tho');
INSERT INTO "Brand" (brandID, supplierID ,brandName, address) VALUES (5, 9, 'BrandE', 'Can Tho');
INSERT INTO "Brand" (brandID, supplierID ,brandName, address) VALUES (6, 2, 'BrandF', 'Can Tho');
INSERT INTO "Brand" (brandID, supplierID ,brandName, address) VALUES (7, 2, 'BrandG', 'Can Tho');
INSERT INTO "Brand" (brandID, supplierID ,brandName, address) VALUES (8, 4, 'BrandH', 'Can Tho');
--Add more data
INSERT INTO "Brand" (brandID, supplierID ,brandName, address) VALUES (9, 6, 'BrandI', 'Can Tho');
INSERT INTO "Brand" (brandID, supplierID ,brandName, address) VALUES (10, 6,'BrandJ', 'Can Tho');
INSERT INTO "Brand" (brandID, supplierID ,brandName, address) VALUES (11, 7,'BrandK', 'Can Tho');
INSERT INTO "Brand" (brandID, supplierID ,brandName, address) VALUES (12, 8,'BrandL', 'Can Tho');
INSERT INTO "Brand" (brandID, supplierID ,brandName, address) VALUES (13, 8,'BrandM', 'Can Tho');
INSERT INTO "Brand" (brandID, supplierID ,brandName, address) VALUES (14, 8,'BrandN', 'Can Tho');
INSERT INTO "Brand" (brandID, supplierID ,brandName, address) VALUES (15, 9,'BrandO', 'Can Tho');

GO
set identity_insert "Brand" off
go
ALTER TABLE "Brand" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
set identity_insert "Warehouse" on
go
ALTER TABLE "Warehouse" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Warehouse" (warehouseID, companyName, warehouseName, address, city, phone, fax, postalCode, description) VALUES (1, 'ZipMart co.', 'Main Warehouse', 'Ninh Kieu', 'Can Tho', '555-1111', '555-2222', '900000', 'Main Warehouse Description');
INSERT INTO "Warehouse" (warehouseID, companyName, warehouseName, address, city, phone, fax, postalCode, description) VALUES (2, 'ZipMart co.', 'Cold Storage', 'Cai Rang', 'Can Tho', '555-2222', '555-3333', '900000', 'Cold Storage Description');
INSERT INTO "Warehouse" (warehouseID, companyName, warehouseName, address, city, phone, fax, postalCode, description) VALUES (3, 'ZipMart co.', 'Electronics Warehouse', 'Binh Thuy', 'Can Tho', '555-4444', '555-7777', '900000', 'Electronics Warehouse Description');
INSERT INTO "Warehouse" (warehouseID, companyName, warehouseName, address, city, phone, fax, postalCode, description) VALUES (4, 'ZipMart co.', 'Food Warehouse', '	Vi Thanh', 'Hau Giang', '555-6453', '555-1234', '900000', 'Food Warehouse Description');

GO
set identity_insert "Warehouse" off
go
ALTER TABLE "Warehouse" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
set identity_insert "Branch" on
go
ALTER TABLE "Branch" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Branch" (branchID, companyName, branchName, address, city, phone, fax, postalCode, description) VALUES (1, 'ZipMart co.', 'ZipMart Ninh Kieu', 'Ninh Kieu', 'Can Tho', '555-1111', '555-2222', '900000', 'Ninh Kieu Branch');
INSERT INTO "Branch" (branchID, companyName, branchName, address, city, phone, fax, postalCode, description) VALUES (2, 'ZipMart co.', 'ZipMart Binh Thuy', 'Ninh Kieu', 'Can Tho', '555-1111', '555-2222', '900000', 'Ninh Kieu Branch');
INSERT INTO "Branch" (branchID, companyName, branchName, address, city, phone, fax, postalCode, description) VALUES (3, 'ZipMart co.', 'ZipMart Cai Rang', 'Ninh Kieu', 'Can Tho', '555-1111', '555-2222', '900000', 'Ninh Kieu Branch');
INSERT INTO "Branch" (branchID, companyName, branchName, address, city, phone, fax, postalCode, description) VALUES (4, 'ZipMart co.', 'ZipMart Phong Dien', 'Ninh Kieu', 'Can Tho', '555-1111', '555-2222', '900000', 'Ninh Kieu Branch');

GO
set identity_insert "Branch" off
go
ALTER TABLE "Branch" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
set identity_insert "Categories" on
go
ALTER TABLE "Categories" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Categories" ("categoryID","CategoryName", "Description", "restockThreshold") VALUES (1,'Household Appliances', 'Home electronic devices', 500);
INSERT INTO "Categories" ("categoryID","CategoryName", "Description", "restockThreshold") VALUES (2,'Spices', 'Various cooking spices', 500);
INSERT INTO "Categories" ("categoryID","CategoryName", "Description", "restockThreshold") VALUES (3,'Meat', 'Various meat products', 500);
INSERT INTO "Categories" ("categoryID","CategoryName", "Description", "restockThreshold") VALUES (4,'Seafood', 'Various seafood products', 500);
INSERT INTO "Categories" ("categoryID","CategoryName", "Description", "restockThreshold") VALUES (5,'Vegetables', 'Fresh vegetables', 500);
INSERT INTO "Categories" ("categoryID","CategoryName", "Description", "restockThreshold") VALUES (6,'Convenience Products', 'Convenient and ready-to-use products', 500);
INSERT INTO "Categories" ("categoryID","CategoryName", "Description", "restockThreshold") VALUES (7,'Fashion', 'Clothing and accessories', 500);
INSERT INTO "Categories" ("categoryID","CategoryName", "Description", "restockThreshold") VALUES (8,'Fruits', 'Fresh fruits', 500);
GO
set identity_insert "Categories" off
go
ALTER TABLE "Categories" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
set identity_insert "Import" on
go
ALTER TABLE "Import" NOCHECK CONSTRAINT ALL
go
INSERT INTO dbo.Import (importID,supplierID, brandID, branchID, categoryID, companyName, dateImport, hsCode, nameProduct, amountDelivery, address, city, phone, fax, postalCode, description, status, leadtime)
VALUES (1, 1, 1, 3, 1, 'ZipMart co.', '2023-01-15', 85166090, 'Kitchen infrared', 100, 'Ninh Kieu', 'Can Tho', '555-1111', '555-2222', '900000', 'Import of Kitchen infrared', 'Pending', 4);
INSERT INTO dbo.Import (importID,supplierID, brandID, branchID, categoryID, companyName, dateImport, hsCode, nameProduct, amountDelivery, address, city, phone, fax, postalCode, description, status, leadtime)
VALUES (2, 1, 2, 3, 6, 'ZipMart co.', '2023-01-15', 73239390, 'Stainless Steel Pot', 30, 'Ninh Kieu', 'Can Tho', '555-4321', '555-2334', '900000', 'Import of Stainless Steel Pot', 'In Progress', 2);
INSERT INTO dbo.Import (importID,supplierID, brandID, branchID, categoryID, companyName, dateImport, hsCode, nameProduct, amountDelivery, address, city, phone, fax, postalCode, description, status, leadtime)
VALUES (3, 1, 1, 3, 1, 'ZipMart co.', '2023-01-15', 85166090, 'oil-free fryer', 50, 'Ninh Kieu', 'Can Tho', '555-2123', '555-4442', '900000', 'Import of Kitchen infrared', 'Pending', 6);
INSERT INTO dbo.Import (importID,supplierID, brandID, branchID, categoryID, companyName, dateImport, hsCode, nameProduct, amountDelivery, address, city, phone, fax, postalCode, description, status, leadtime)
VALUES (4, 1, 2, 3, 6, 'ZipMart co.', '2023-01-15', 70133700, 'Glass Bowl', 90, 'Ninh Kieu', 'Can Tho', '555-6123', '555-5213', '900000', 'Import of Glass Bowl', 'Completed', 4);

GO
set identity_insert "Import" off
go
ALTER TABLE "Import" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
set identity_insert "InventoryStatus" on
go
ALTER TABLE "InventoryStatus" NOCHECK CONSTRAINT ALL
go
INSERT INTO dbo.InventoryStatus (statusID,statusName) VALUES (1, 'INSTOCK');
INSERT INTO dbo.InventoryStatus (statusID,statusName) VALUES (2, 'LOWSTOCK');
INSERT INTO dbo.InventoryStatus (statusID,statusName) VALUES (3, 'OUTOFSTOCK');

GO
set identity_insert "InventoryStatus" off
go
ALTER TABLE "InventoryStatus" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
set identity_insert "Products" on
go
ALTER TABLE "Products" NOCHECK CONSTRAINT ALL
go
INSERT INTO dbo.Products (productID, warehouseID, categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (1, 3, 1, 1, 1,'Iron', 'img\products\homeelectric\banuikho.jpg',15.00,50,150,20,'unit',20,
'<p>
	<span style="font-weight: 400">Goldsun Gir2202 Dry Iron will be an indispensable device in the family, considered a modern iron, helping to iron clothes quickly, saving time and effort for the user.</span>
</p>' +
'<p>
	<span style="font-weight: 400">Outstanding features: Anti-slip handle design. The table top is covered with high quality non-stick Teflon. Remove wrinkles from all fabrics. 3 levels of temperature adjustment. Automatically turns off power when not in use.</span>
</p>' +
'<p>
	<span style="font-weight: 400">Technical specifications: Power: 1000W, 3 temperature adjustment levels, 3 ironing temperature levels. Surface covered with Teflon non-stick coating. Power source 220V~50HZ. Weight: 0.6kg</span>
</p>' + 
'<p>
	<span style="font-weight: 400">Note when using: Low temperature for synthetic fibers/silk. Average temperature used for fabrics. High temperature for linen and cotton. Place of production: Vietnam.</span>
</p>', 200, 15, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (2, 3, 1, 1, 1,'Mini Gas Stove', 'img\products\homeelectric\bepgasmini.jpg',8.43,50,150,20,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>NaMilux Mini gas stove NH-P3031PF</strong> is a convenient gas stove model with new improvements in extremely strong capacity, shortening cooking time and minimizing gas waste.
        </li>
        <li aria-level="1" style="font-weight: 400">
          Use an automatic gas shut-off valve assembly to ensure safety when used.
        </li>
        <li aria-level="1" style="font-weight: 400">The heat transfer plate design keeps the pressure in the gas can stable, giving a strong, blue fire throughout the cooking process.</li>
        <li aria-level="1" style="font-weight: 400">Anti-slip tripod legs provide sturdy support, can be used for many sizes of pots and pans, and limit heat transfer to gas cans to avoid causing fires and explosions when in use.</li>
        <li aria-level="1" style="font-weight: 400">The product uses eyelid rolling technology <i class="fa-thin fa-arrow-right"></i> creates sturdiness and easy cleaning.</li>
		<li aria-level="1" style="font-weight: 400">Heat-resistant electroplated brackets (3500 degrees Celsius), bracket trays to prevent water from spilling into gas cans.</li>
</ul>', 200, 15, 3);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (3, 3, 1, 1, 1,'Kitchen infrared', 'img\products\homeelectric\bephongngoai.jpg',17.5,20,100,15,'unit',0,
'<p>
	<span style="font-weight: 400">Sunhouse SHD6014 Infrared Stove is simply designed, super thin with a weight of 2kg, you can move it anywhere conveniently. The product is designed with two eye-catching main colors: black and white, the kitchen surface is made from a super durable material that is heat-resistant and durable, easy to observe.</span>
</p>' +
'<p>
	<span style="font-weight: 400">Infrared stoves use modern technology, do not radiate heat, so they are very safe for users''health. The stove has a safety lock so you can rest assured when children come near. The super durable glass surface can withstand 800 degrees Celsius, prevents scratches and cracks at high temperatures, and the cooling fan helps radiate heat quickly, prevents short circuits, and increases the life of the stove.</span>
</p>' +
'<p>
	<span style="font-weight: 400">Operating with a capacity of 2000W to cook food faster, smart timer function helps save energy and time</span>
</p>' + 
'<p>
	<span style="font-weight: 400">Using electronic chips to control, many different cooking modes such as stir-frying, stewing, hot pot, frying,... make cooking easier and simpler.</span>
</p>', 100, 27, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (4, 3, 1, 2, 1,'Lock&Lock Grill EJG232 - Black', 'img\products\homeelectric\noichienkhongdau.jpg',15.00,50,150,20,'unit',0,
'<p>
	<span style="font-weight: 400">Lock&Lock Grill EJG232 Black has a compact, simple but modern design, suitable for your kitchen space.</span>
</p>' +
'<p>
	<span style="font-weight: 400">The product is used to grill food, with an effective non-stick grill surface, creating dishes with less oil, still retaining a delicious taste, ensuring the health of the user.</span>
</p>' +
'<p>
	<span style="font-weight: 400">Product specifications: Dimensions: 560x330x85mm; Weight: 2kg/ including 2.8kg packaging.</span>
</p>' + 
'<p>
	<span style="font-weight: 400">Notes when using: - Keep out of reach of children. Do not leave the product near places containing water or humid air, and avoid direct sunlight. - Avoid strong impacts, dropping the product and pay attention to electrical leakage incidents. - Only use the product for basic purposes, do not use it for other commercial purposes.</span>
</p>', 50, 0, 2);
----------------------------------------
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (5, 1, 2	, 3, 3,'Simply Pure 100% Soybean Oil Bottle', 'img\products\spice\dauanhatcaiSimply.jpg',0.53,50,150,45,'unit',0,
'<p>
	<span style="font-weight: 400">Simply Pure 100% Canola Oil 1L Bottle is produced from premium imported ingredients, retaining high levels of unsaturated fat (> 88%) to help protect heart health. The product also adds a rich source of vitamin A in sunflower oil that can prevent cataracts.</span>
</p>' +
'<p>
	<span style="font-weight: 400">Simply Canola Oil is produced using modern refining technology from Europe, strictly complying with international standards on food safety and hygiene: FSSC 22000, HACCP and AIB. The product does not contain cholesterol and trans fatty acids, preservatives and colorants.</span>
</p>' +
'<p>
	<span style="font-weight: 400">The product is conveniently bottled, the bottle is made from clean ingredients, free of impurities, ensuring consumer health.Simply is a cooking oil brand recommended by the Vietnam Heart Association.</span>
</p>' + 
'<p>
	<span style="font-weight: 400">Instructions for use: Fry, stir-fry, mix salad, make cakes, cook vegetarian dishes. Store in a cool and dry place, away from direct sunlight. Close the lid tightly after use.</span>
</p>', 500, 0, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (6, 1, 2, 4, 5,'Cholimex Char Siu Sauce', 'img\products\spice\sotxaxiuCholimex.jpg',2.21,97,234,59,'unit',0,
'<p>
	<span style="font-weight: 400">Cholimex Char Xiu Sauce 200G is packaged beautifully, neatly, and is easy to preserve. The product helps housewives save a lot of time when cooking.</span>
</p>' +
'<p>
	<span style="font-weight: 400">The product does not contain toxic chemicals or preservatives, ensuring safety for consumers health.</span>
</p>' +
'<p>
	<span style="font-weight: 400">Cholimex sauce is produced using modern, closed technology under strict supervision and inspection, helping to cook char siu meat faster and more convenient.</span>
</p>' , 434, 0, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (7, 1, 2, 4, 5,'Fuji Soy Sauce Chai', 'img\products\spice\nuoctuongPhuSi.jpg',0.69,100,543,90,'unit',0,
'<p>
	<span style="font-weight: 400">Fuji Soy Sauce is extracted from soybeans, bringing a delicious, attractive taste to your family meals.</span>
</p>' +
'<p>
	<span style="font-weight: 400">Manufactured using modern, advanced Japanese technology. Ingredients: Water, salt, naturally fermented soybean extract (kiage), vegetable protein,...Use directly or marinate meat, fish, braised dishes, stir-fry, and cook.</span>
</p>' +
'<p>
	<span style="font-weight: 400">Storage: Dry, cool place, avoid direct sunlight, cover tightly after use. Made in Viet Nam.</span>
</p>' , 234, 5, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (8, 1, 2, 4, 5,'Chinsu Chili Sauce', 'img\products\spice\tuongotChinsu.jpg',1.56,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>NaMilux Mini gas stove NH-P3031PF</strong> Chili sauce is made from ripe red chili peppers with the strong aroma of garlic and subtle variations of spices to fill each chili sauce line of the Chinsu chili sauce brand.
        </li>
        <li aria-level="1" style="font-weight: 400">
          Chinsu chili sauce 1kg large bottle is convenient and suitable for large families, helping to save money.
        </li>
</ul>', 223, 0, 1);
--------------------------
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (9, 2, 3, 5, 9,'Tam Nong whole chicken', 'img\products\meat\gatanguyenconTamNong.jpg',0.22,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>Tam Nong whole chicken 1.4kg is raised according to strict standards, under the supervision of experts.
        </li>
        <li aria-level="1" style="font-weight: 400">Chickens are healthy, guaranteed not to be infected with flu or disease. Closed and safe livestock farming system. Raising livestock safely, using clean, high-quality food sources. Do not use weight gain substances or stimulants to help chickens grow bigger. The finished chicken processing factory system is clean, using modern technology, ensuring food hygiene and safety requirements.</li>
		<li aria-level="1" style="font-weight: 400">Chicken is carefully packaged and preserved to deliver to consumers, keeping product quality always the best. Tam Nong chicken can be used to prepare many delicious dishes such as: boiled chicken, stewed chicken, grilled chicken, fried chicken, fried chicken.</li>
</ul>', 223, 0, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (10, 2, 3, 5, 9,'Imported beef shoulder core', 'img\products\meat\loivaibo.jpg',0.23,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>The shoulder core is cut from the meat area between the shoulder and neck of the cow. In the middle of the tenderloin there is a thin, crispy and not tough tendon.
        </li>
        <li aria-level="1" style="font-weight: 400">
          The meat is dark red in color, the shoulder core is moderately soft because it has an evenly distributed amount of fat and lean and is sweet and fragrant with a characteristic grain flavor.
        </li>
        <li aria-level="1" style="font-weight: 400">Beef shoulder steak contains several essential nutrients including protein, iron, zinc, selenium, riboflavin, niacin, vitamin B6, vitamin B12, phosphorus, pantothenate, magnesium and potassium.</li>
</ul>', 223, 0, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (11, 2, 3, 5, 9,'CP chicken fillet', 'img\products\meat\philega.jpg',0.24,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>CP Chicken Fillet 500g (ea) is made from clean chicken sources, raised in a farm system that meets strict standards.</li>
        <li aria-level="1" style="font-weight: 400">
          The preliminary processing and preservation process is carried out in a closed process, ensuring food hygiene and safety.
        </li>
        <li aria-level="1" style="font-weight: 400">Chicken is rich in vitamins A, B1, B2, C, E, acid, calcium, phosphorus, iron that the human body easily absorbs and digests. Good for the heart, fights depression, supports teeth and bones, promotes eye health.</li>
		<li aria-level="1" style="font-weight: 400">The nutritional content of chicken breast is high and the amount of fat is low. Suitable for people who are in the process of losing weight and babies who are weaning.</li>
</ul>', 223, 0, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (12, 2, 3, 5, 9,'Pork Ribs', 'img\products\meat\suonnonheo.jpg',0.25,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>Pork ribs are a very popular cut of meat with soft meat, balanced with fat, keeping the meat from drying out. In particular, the cartilage is both soft and crunchy, bringing a pleasant feeling when eating.</li>
        <li aria-level="1" style="font-weight: 400">
          Pork ribs are a good source of protein, providing essential amino acids for muscle growth and maintenance, as well as other body functions. Pork contains selenium, an important mineral that has antioxidant properties and supports the immune system.
        </li>
        <li aria-level="1" style="font-weight: 400">Pork ribs are often used to cook many delicious and diverse dishes such as grilled, stir-fried, braised, steamed, cooked in soup, grilled, or processed into different dishes.</li>
		<li aria-level="1" style="font-weight: 400">Pigs are raised using advanced technology and a closed meat collection process, ensuring food safety and hygiene. The product is packaged in a convenient, hygienic tray when it reaches the consumer.</li>
</ul>', 223, 0, 1);
------------------------------
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (13, 2, 4, 5, 9,'Fresh Octopus', 'img\products\aquaticproducts\bachtuoctuoi.jpg',0.26,89,132,60,'unit',0,
'<p>
	<span style="font-weight: 400">Fresh octopus meat provides essential vitamins for the body such as: A, B1, B2, PP, C and some other minerals such as calcium, phosphorus...</span>
</p>' +
'<p>
	<span style="font-weight: 400">Octopus meat has many nutrients for body development such as zinc, copper, iron, iodine - very good for brain development.</span>
</p>' +
'<p>
	<span style="font-weight: 400">Octopus meat has almost no fat and is very beneficial for muscles. This type of seafood is also rich in nutrients and has the effect of strengthening physical strength.</span>
</p>', 223, 0, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (14, 2, 4, 5, 9,'Tuna fillet', 'img\products\aquaticproducts\cangudaiduongphile.jpg',0.27,89,132,60,'unit',0,
'<p>
	<span style="font-weight: 400">Ocean tuna is prepared from fresh ocean tuna, going through a careful selection process, closed processing and packaging process, ensuring all food hygiene and safety standards, safe for health.</span>
</p>' +
'<p>
	<span style="font-weight: 400">Ocean tuna has high protein content, contains a lot of magnesium minerals, a mixture of B vitamins, potassium, a source of omega 3,... bringing great health benefits such as helping to: lose weight; eye health; prevent atherosclerosis; Activate brain cells and promote brain activities;....</span>
</p>' , 223, 0, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (15, 2, 4, 5, 9,'Saury', 'img\products\aquaticproducts\cathudao.jpg',0.30,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>Saury (sanma) caught mainly in September-October contains many healthy oils and is considered a famous specialty of Japanese autumn. During the Edo period (1603 - 1868), this shiny green-skinned fish was considered a low-grade food, while white fish were much more popular.</li>
        <li aria-level="1" style="font-weight: 400">
          Fish contains a lot of fat and nutrients that are beneficial for health. Fish fat contains DHA and EPA, which are substances that have the ability to prevent cardiovascular diseases such as myocardial infarction and stroke. In addition, saury meat contains a lot of vitamins that are good for health such as Vitamin E, vitamin A, and vitamin D.
        </li>
        <li aria-level="1" style="font-weight: 400">How to prepare saury: After buying saury, clean the fish belly. However, mackerel has the characteristic of having firm meat (a lot of meat), so the intestines are not much, making it very easy to clean. Soak the fish in diluted salt water, use a knife to gently scrape the fish skin to remove plaque. Then, cut the mackerel gills into bite-sized pieces or chop them to suit your cooking needs. Delicious dishes from saury: Mackerel in tomato sauce, fragrant braised mackerel, braised mackerel with tomatoes, grilled mackerel...</li>
		<li aria-level="1" style="font-weight: 400">How to preserve fresh saury: Saury should be prepared immediately to maintain its freshness, but if you can use it in time, you can store it in the refrigerator.</li>
</ul>', 223, 0, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (16, 2, 4, 5, 9,'Red tilapia', 'img\products\aquaticproducts\cadieuhong.jpg',0.98,89,132,60,'unit',0,
'<p>
	<span style="font-weight: 400">Red tilapia or red tilapia, also known as red tilapia, is a freshwater fish belonging to the tilapia family. As a type of fish with delicious meat quality, red tilapia meat is white, clean, the meat fibers are firmly structured and especially the meat does not have too many bones.</span>
</p>' +
'<p>
	<span style="font-weight: 400">Red tilapia is prepared from raw red tilapia, removing scales and internal organs, helping housewives save time in cleaning and easily prepare delicious dishes for the family quickly.</span>
</p>' +
'<p>
	<span style="font-weight: 400">Red tilapia meat contains selenium content that helps enhance the function of white blood cells. Supports the thyroid gland and increases antioxidants to help prevent cancer, rheumatism and cardiovascular disease. Potassium reduces the risk of stroke, osteoporosis, kidney stones and high blood pressure. Selenium is a compound that helps reduce pain and prevent disease. In addition, red tilapia meat also contains vitamins, especially Vitamin A supports beauty and enhances vision.</span>
</p>', 223, 0, 1);
-----------------------------
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (17, 4, 5, 6, 2,'Broccoli', 'img\products\vegetable\bongcaixanh.jpg',0.54,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>Broccoli (also known as broccoli) is a vegetable belonging to the cruciferous family with the scientific name Brassica Oleracea. Broccoli is a green vegetable shaped like a miniature tree, along with kale and cauliflower and are both cruciferous vegetables.</li>
        <li aria-level="1" style="font-weight: 400">
          Broccoli is high in nutrients, including fiber, vitamin C, vitamin K, iron and potassium. Broccoli also contains more protein than most other vegetables.</li>
        <li aria-level="1" style="font-weight: 400">The product does not contain harmful chemicals or growth stimulants, ensuring safety for consumers health. Therefore, you can completely feel secure when choosing this product for every meal your family has, making meals more delicious.</li>
</ul>', 223, 0, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (18, 4, 5, 6, 2,'Da Lat Beets', 'img\products\vegetable\cudenDaLat.jpg',0.56,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>Dalat Beetroot is a clean food, containing many nutrients such as fiber, vitamin A, potassium... good for the body. The active ingredients in fresh beets have the effect of nourishing the blood, strengthening the body immune components, helping to detect and eliminate abnormal cells before they can transform into cancer cells.</li>
        <li aria-level="1" style="font-weight: 400">Products are grown using modern technology, ensuring freshness and food hygiene and safety.</li>
</ul>', 223, 0, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (19, 4, 5, 7, 2,'Iceberg Lettuce', 'img\products\vegetable\xalachIceberg.jpg',3.43,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>Iceberg Lettuce (also known as head lettuce) is a type of lettuce originating from America. Ice Berg Lettuce is tightly rolled into large, heavy heads, similar to cabbage buds.</li>
        <li aria-level="1" style="font-weight: 400">Lettuce is considered one of the healthiest vegetables, rich in vitamins C, K and anthocyanin polyphenols, which help prevent oxidation, reduce the risk of cardiovascular disease, diabetes and some cancers.</li>
		<li aria-level="1" style="font-weight: 400">Thanks to being rich in antioxidants, regular use of the product also makes your skin softer and brighter.</li>
</ul>', 223, 0, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (20, 4, 5, 7, 2,'Da Lat Beef Tomatoes', 'img\products\vegetable\cachuaBeefDaLat.jpg',2.31,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>Beef tomatoes are large fruits, with an average fruit weight of 110-200 grams. The fruit is red, ripens early, and has a dense flesh. This type of tomato has won and won the American AAS (All-Amer Selections) award.</li>
        <li aria-level="1" style="font-weight: 400">Beef tomatoes are thick, firm, have few seeds, and have few gaps when slicing. Beef tomatoes weigh more than regular tomatoes. Tomatoes are grown using modern technology, ensuring freshness and food hygiene and safety.</li>
</ul>', 223, 10, 1);
---------------------------
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (21, 1, 6, 8, 4,'Happycook HEK-180PW 1.8L Electric Kettle', 'img\products\convenientproduct\amdunsieutoc.jpg',5.98,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>Compact design, light weight, a familiar household appliance in every Vietnamese family today. Capacity of 1.8 liters is suitable for families of 2 - 4 members or in offices, companies,...</li>
        <li aria-level="1" style="font-weight: 400">
          The device is simply constructed and made from highly durable 201 stainless steel inside. The device will automatically shut off when it reaches a certain boiling point, ensuring anti-overheating and safety when boiling. The kettle shell is multi-layered with a hard, shiny plastic outer shell.</li>
        <li aria-level="1" style="font-weight: 400">Large capacity heating plate: The Happy Cook kettle electric heating plate operates stably with a capacity of up to 1,500W, allowing water heating time to be shortened, meeting hot water needs in just a few minutes.</li>
</ul>', 223, 15, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (22, 1, 6, 9, 6,'Nano Cyclone Wet Towels 20 Pieces', 'img\products\convenientproduct\khanuotnano.jpg',4.32,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>Nano Popular Premium Wet Towels 20 Pieces are soft, smooth wet towels and are a useful product for your life, helping you save time and shopping costs.</li>
        <li aria-level="1" style="font-weight: 400">
          The product does not contain alcohol and does not cause skin sensitivity but has a gentle fragrance that creates a feeling of coolness, comfort and confidence.</li>
        <li aria-level="1" style="font-weight: 400">The product is made from non-woven fabric, purified water, natural fragrances, and humectants that help keep and provide necessary moisture to the skin, keeping the skin fresh, clean, and not dry after wiping.</li>
</ul>', 223, 0, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (23, 1, 6, 9, 6,'Nestlé Milo Breakfast Cereal Box', 'img\products\convenientproduct\ngucocansang.jpg',2.11,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>Nestlé MILO breakfast cereal 300g box is extracted from barley germ and grains containing many minerals, iron, vitamins, calcium and iodized salt to give your baby abundant energy to start a new day.</li>
        <li aria-level="1" style="font-weight: 400">
          The cake has the familiar Milo flavor, is crispy and fragrant and is loved by children. It is easy to eat and not boring. It can be used with milk to increase appetite and create an attractive breakfast.</li>
        <li aria-level="1" style="font-weight: 400">The product is conveniently packaged, mothers can easily prepare it for their children to take to school or on family picnics. Ingredients: Cereal flour, oatmeal, rice flour, sugar, glucose syrup, palm oil, honey, minerals...</li>
</ul>', 223, 20, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (24, 1, 6, 10, 6,'Lipton Lemon Honey Lemon Tea Box', 'img\products\convenientproduct\trachanhLipton.jpg',1.89,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>Lipton Lemon Honey Lemon Tea Box of 16 Packs 12G is made from natural ingredients with a great combination of black tea leaves and natural lemon flavor to bring a refreshing feeling when enjoying.</li>
        <li aria-level="1" style="font-weight: 400">It great to enjoy a cool cup of honey lemon tea after stressful working hours or in hot sunny weather. Lemon tea provides vitamin C to help strengthen the immune system, and the delicious lemon scent will bring a refreshing feeling full of vitality.</li>        <li aria-level="1" style="font-weight: 400">The product is conveniently packaged, mothers can easily prepare it for their children to take to school or on family picnics. Ingredients: Cereal flour, oatmeal, rice flour, sugar, glucose syrup, palm oil, honey, minerals...</li>
        <li aria-level="1" style="font-weight: 400">The product is produced using a closed technology to help preserve the full flavor of the leaves as well as the cool taste of lemon.</li>
</ul>', 223, 5, 1);
--------------------------------------
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (25, 1, 7, 12, 8,'Boys Short-Sleeved T-shirt with 2-sided Print in Light Blue Size 11-14', 'img\products\fashion\aothuntayngan.jpg',3.42,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>Baby Boy T-shirt with 2-sided Print in Light Blue Size 11-14 is a simple short-sleeved, round-neck shirt, but the print on the front and back of the shirt makes your baby look very stylish, the fabric Breathable and absorbent cotton provides comfort when outdoors, helping your baby feel comfortable and confident when moving.</li>
        <li aria-level="1" style="font-weight: 400">Mothers can combine the shirt with jeans, boy shorts, etc. with children sports shoes and sandals to give the baby a unique personality.</li>  
</ul>', 223, 5, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (26, 1, 7, 12, 8,'Automatic Folding Umbrella (Random Color Delivery)', 'img\products\fashion\ogap3Ocean.jpg',0.21,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>Automatic Folding Umbrella (Random Color) has a sturdy, durable metal frame, durable and water-resistant umbrella material. The Automatic Folding Umbrella is designed to be compact and lightweight, easy to install quickly, and easy to carry with you.</li>
        <li aria-level="1" style="font-weight: 400">The product has a 2-way automatic folding and opening design that is convenient for users. The product comes in many colors and is delivered randomly. Made in Viet Nam.</li>  
</ul>', 223, 0, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (27, 1, 7, 13, 8,'Women Onoff Collarless Socks with Star Floral Pattern 146-Sw01 (Random Color)', 'img\products\fashion\vonukhongco.jpg',0.21,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>Women OnOff Collarless Socks with Star Floral Pattern 146-SW01 use natural Cotton material with OEKO-Tex safety material certification, the socks are soft and sweat-absorbent, keeping feet dry and comfortable.</li>
        <li aria-level="1" style="font-weight: 400">Y-shaped heel design keeps the socks close to the feet, soft elastic collar, light hug, antibacterial and deodorizing. Freesize socks; Colors include: white with gray collar, pink collar, white with pink collar, blue with gray collar, pink with gray collar, black delivered randomly.</li>  
        <li aria-level="1" style="font-weight: 400">Care instructions: Wash with water below 40 degrees Celsius, do not dry at high temperatures, do not iron, do not use strong detergents. Made in Viet Nam.</li>  
</ul>', 223, 0, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (28, 1, 7, 14, 8,'Women Double Striped Elastic Shorts Size L (Random Color Delivery)', 'img\products\fashion\quanshortthun.jpg',0.21,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>Women double-striped elastic shorts use soft, smooth fish skin elastic material, with air holes created by overlapping layers of fish skin, giving the wearer a cool, sweat-absorbing feeling. Besides, the pants have meticulous seams, providing standard form, high durability as well as a dynamic and comfortable feeling for girls to wear.</li>
        <li aria-level="1" style="font-weight: 400">The pants have a comfortable shorts design, an elastic waistband with a youthful and stylish drawstring, and double stripes on the side to create a personality and dynamism for girls to wear.</li>  
        <li aria-level="1" style="font-weight: 400">Many fashionable, youthful colors, allowing women to easily mix & match with different types of shirts. Care instructions: Wash at normal temperature, do not use bleach, dry in the shade. Made in Viet Nam.</li>  
</ul>', 223, 0, 1);
------------------------------
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (29, 4, 8, 15, 9,'Chinese Soft Red Pomegranate', 'img\products\fruit\luuhatdoTrungQuoc.jpg',0.21,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>The Chinese soft red pomegranate is a fruit rich in antioxidants and has a delicious flavor, so it is very popular. It has red flesh, thick, succulent flesh, soft edible seeds, and a pink-yellow skin.</li>
        <li aria-level="1" style="font-weight: 400">Pomegranate contains many nutrients, especially high fiber content that is good for the digestive system. Calcium, vitamin A, vitamin E and folic acid in pomegranates are very beneficial in strengthening the body resistance.</li>  
        <li aria-level="1" style="font-weight: 400">Plant compounds in pomegranates have anti-inflammatory effects. Studies show that pomegranate extract can block enzymes that cause joint damage in people with osteoarthritis.</li>  
</ul>', 223, 0, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (30, 4, 8, 15, 9,'Cantaloupes of the Dutch variety', 'img\products\fruit\dualuoiHaLan.jpg',5.32,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>Dutch Cantaloupes are grown using Israeli technology with strict supervision, creating cantaloupe varieties with high sugar content, firm flesh, green flesh, round fruit, and longer-lasting sweetness.</li>
        <li aria-level="1" style="font-weight: 400">Cantaloupe is a source of polyphenol antioxidants, which are beneficial for health in preventing cancer and strengthening the immune system.
Besides, cantaloupe contains a lot of fiber so it has a laxative effect and prevents constipation. Melon contains the largest amount of digestive enzymes among fresh fruits, more than papaya and mango. In addition, cantaloupe is also a rich source of beta-carotene, folic acid, potassium and vitamins C and A.</li>  
</ul>', 223, 20, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (31, 4, 8, 11, 7,'Cripps Red apples imported from South Africa', 'img\products\fruit\taoNamPhi.jpg',0.21,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>South African Cripps Red apples are one of the imported South African apple varieties that are loved by many consumers with unique characteristics. Cripps Red apples are crunchy, sweet imported fruits with a mildly sour taste.</li>
        <li aria-level="1" style="font-weight: 400">Apples contain many important nutrients, including fiber, vitamins, minerals and antioxidants. In addition, apples contain a lot of vitamin A, vitamin B, vitamin D, etc.</li>  
</ul>', 223, 0, 1);
INSERT INTO dbo.Products (productID,warehouseID,categoryID,brandID,supplierID,productName,imageURL,unitPrice,quantity,quantityInStock,quantitySold,unit,newAdjustment,description,viewCount,discount,avaliable)
VALUES (32, 4, 8, 11, 7,'Guava variety from Taiwan', 'img\products\fruit\oiDaiLoan.jpg',0.21,89,132,60,'unit',0,
'<ul>
        <li aria-level="1" style="font-weight: 400">
          <strong>Taiwanese guava is also known as Taiwanese pear guava because the fruit is large like a pear. Taiwanese guavas have large, shiny fruits that are very crunchy, sweet and have very few seeds, so they are very popular.</li>
        <li aria-level="1" style="font-weight: 400">This guava fruit contains many beneficial nutrients for the body such as Vitamin C and B vitamins that increase disinfection of the digestive system and are beneficial for the stomach and intestines.
Guava Pear can be eaten directly or made into juice to supplement nutrition for the body. You should especially eat guava peel because guava peel contains a lot of vitamin C, which is very good for beautifying the skin.</li>  
</ul>', 223, 0, 1);

GO
set identity_insert "Products" off
go
ALTER TABLE "Products" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
set identity_insert "ThresholdAdjustment" on
go
ALTER TABLE "ThresholdAdjustment" NOCHECK CONSTRAINT ALL
go
INSERT INTO "ThresholdAdjustment" (ID, productID, reasonAdjustment, new_restockThreshold, dateAdjusted, statusThresholdAdjustments) VALUES (1, 1, 'Low Inventory', 20, '2023-01-15 08:30:00', 'Accept');
INSERT INTO "ThresholdAdjustment" (ID, productID, reasonAdjustment, new_restockThreshold, dateAdjusted, statusThresholdAdjustments) VALUES (2, 2, 'Excess Inventory', 20, '2023-02-20 10:45:00', 'Pending');
GO
set identity_insert "ThresholdAdjustment" off
go
ALTER TABLE "ThresholdAdjustment" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
set identity_insert "Orders" on
go
ALTER TABLE "Orders" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (1, 1, 1, '2023-01-15 08:30:00', '2023-01-18 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (2, 2, 1, '2023-01-15 08:30:00', '2023-01-18 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (3, 3, 2, '2023-01-16 09:30:00', '2023-01-18 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (4, 4, 2, '2023-01-16 09:30:00', '2023-01-18 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
---------------------------------
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (5, 5, 3, '2023-01-17 10:30:00', '2023-01-20 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Pending');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (6, 6, 3, '2023-01-17 10:30:00', '2023-01-20 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Pending');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (7, 7, 4, '2023-01-18 11:00:00', '2023-01-20 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Pending');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (8, 8, 4, '2023-01-18 11:00:00', '2023-01-20 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (9, 9, 2, '2023-01-19 11:00:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Pending');
------------------------------
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (10, 10, 2, '2023-01-19 11:00:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (11, 11, 1, '2023-01-20 15:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (12, 11, 1, '2023-01-20 15:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Pending');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (13, 12, 3, '2023-01-20 15:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (14, 13, 3, '2023-01-20 18:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (15, 14, 4, '2023-01-21 08:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (16, 15, 4, '2023-01-21 08:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
------------------------------
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (17, 16, 3, '2023-01-22 08:30:00', '2023-01-25 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (18, 17, 3, '2023-01-22 15:30:00', '2023-01-25 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (19, 17, 2, '2023-01-23 18:30:00', '2023-01-25 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (20, 18, 2, '2023-01-24 08:30:00', '2023-01-30 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (21, 1, 4, '2023-01-25 18:30:00', '2023-01-30 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
-----------------------------
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (22, 2, 4, '2023-01-26 08:30:00', '2023-01-30 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (23, 2, 1, '2023-01-27 15:30:00', '2023-01-30 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (24, 3, 1, '2023-01-28 08:30:00', '2023-01-30 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (25, 4, 1, '2023-01-29 18:30:00', '2023-02-03 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
INSERT INTO "Orders" (orderID, customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (26, 5, 1, '2023-01-30 15:30:00', '2023-02-03 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');

GO
set identity_insert "Orders" off
go
ALTER TABLE "Orders" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
set identity_insert "OrdersDetails" on
go
ALTER TABLE "OrdersDetails" NOCHECK CONSTRAINT ALL
go
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (1, 1, 1, 15.00, 1, 15, 12.75, 'credit card', 'Pham Hieu Tho', '1234567890123456');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (2, 2, 2, 8.43, 2, 15, 14.33, 'credit card', 'Ly Minh Nghia', '2345678901234567');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (3, 3, 3, 7.15, 1, 27, 5.22, 'credit card', 'Pham Huu Nhan', '3456789012345678');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (4, 4, 4, 15.00, 3, 0, 45.00, 'prepaid card', 'Nguyen Minhh Tuan', '4567890123456789');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (5, 5, 5, 0.53, 1, 0, 0.53, 'prepaid card', 'Nguyen Thi My Hao', '5678901234567890');
--------------------------
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (6, 6, 6, 2.21, 1, 0, 2.21, 'prepaid card', 'Ong Kim Thanh', '6789012345678901');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (7, 7, 7, 0.69, 1, 0,  0.69, 'debit card', 'Nguyen Trong Tri', '7890123456789012');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (8, 8, 8, 1.56, 3, 0, 4.68, 'debit card', 'Nguyen Tan Phat', '8901234567890123');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (9, 9, 9, 0.22, 3, 0,  0.66, 'debit card', 'Truong Ngoc Anh', '9012345678901234');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (10, 10, 1, 15.00, 1, 15, 12.75, 'credit card', 'Nguyen Thi Anh Thu', '0123456789012345');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (11, 11, 10, 0.23, 1, 0, 0.23, 'credit card', 'Pham Van Hien', '1234567890123456');
------------------------------
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (12, 12, 1, 15.00, 1, 15, 12.75, 'credit card', 'Pham Van Hien', '1234567890123456');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (13, 13, 1, 15.00, 5, 15, 63.75,  'credit card', 'To Ngoc Lan', '2345678901234567');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (14, 14, 20, 2.31, 1, 10, 2.08, 'prepaid card', 'Truong Thi Ngoc Trang', '3456789012345678');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (15, 15, 21, 2.31, 1, 15, 1.96 , 'prepaid card', 'Nguyen Ngoc Hien', '4567890123456789');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (16, 16, 21, 2.31, 1, 15, 1.96 , 'prepaid card', 'Nguyen Thanh Dat', '5678901234567890');
------------------------------
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (17, 17, 21, 2.31, 1, 15, 2.08, 'debit card', 'Dang Minh Dat', '6789012345678901');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (18, 18, 22, 4.32, 3, 0, 12.96, 'debit card', 'Tran Thi Thu Thao', '7890123456789012');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (19, 19, 23, 2.11, 3, 25, 4.75, 'debit card', 'Tran Thi Thu Thao', '7890123456789012');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (20, 20, 24, 1.89, 3, 5, 5.39, 'debit card', 'Vuong Nguyen Kim Tuyen', '8901234567890123');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (21, 21, 25, 3.42, 3, 5, 9.75, 'credit card', 'Pham Hieu Tho', '1234567890123456');
-----------------------------
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (22, 22, 30, 5.32, 1, 20, 4.26, 'credit card', 'Ly Minh Nghia', '2345678901234567');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (23, 23, 30, 5.32, 1, 20, 4.26, 'credit card', 'Ly Minh Nghia', '2345678901234567');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (24, 24, 30, 5.32, 1, 20, 4.26, 'credit card', 'Pham Huu Nhan', '3456789012345678');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (25, 25, 30, 5.32, 1, 20, 4.26, 'prepaid card', 'Nguyen Minhh Tuan', '4567890123456789');
INSERT INTO "OrdersDetails" (ID, orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (26, 26, 30, 5.32, 1, 20, 4.26, 'prepaid card', 'Nguyen Thi My Hao', '5678901234567890');

GO
set identity_insert "OrdersDetails" off
go
ALTER TABLE "OrdersDetails" CHECK CONSTRAINT ALL
go
GO

-----------------------------------

GO
USE [master]
GO
ALTER DATABASE [ZipMart] SET  READ_WRITE 
GO
