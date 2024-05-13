<!doctype html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="passport.css?version=31">
    <script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width">
    <title>Passport</title>
</head>
<body ng-app="myApp">
<div class="container-fluid" ng-controller=itemsController>
     <div class="panel panel-default">
        <div class="panel-heading">
          <p class="panel-title total-amount">Total: <span></span></p>
          <p class="panel-title item-display">Item: <span ng-hide='product==undefined'>{{product.name}}</span></p>
        </div>
        
        <div class="panel-body">
          <form name="cashRegister" class="form-horizontal">
            <!-- Product Selector -->
            <div class="form-group">
              <div class="col-md-4 product-inputs-left ui-widget">
                <input class="form-control input-lg" list="product_names" type="text" ng-model="inputed_product_name" ng-change="select_product()">
                  <datalist id="product_names">
                    <option ng-repeat="a in product_list" value="{{a.name}}">
                  </datalist>
                <span class="help-block help-block-label">Type item</span>
              </div>
              <div class="col-md-2 product-inputs">
                <input class="form-control input-lg" type="number" ng-model="inputed_product_quantity" ng-change="select_quantity()">
                <span class="help-block help-block-label">Choose Quantity</span>
              </div>
              <div class="col-md-3 product-inputs-right">
                <button class="btn btn-lg btn-primary btn-block" id="btnAddProduct" ng-click=add_to_cart()>ADD</button>
                <span class="help-block help-block-label">Add to Cart</span>
              </div>
          </form>
          
          <!-- Cart contents -->
          <div class="well" id="cartDisplay">
            <table  class="table table-condensed table-striped table-responsive table-borderless" ng-model=discount_row>
              <tr>
                <th>{{discount_row[discount_row.length-1].name}}</th>
                <th>{{discount_row[discount_row.length-1].discount}}</th>
               </tr>
               <tr ng-repeat="x in cart">
                <td>{{x.name}}</td>
                <td>{{x.quantity}}</td>
                <td><button class="btn btn-danger" ng-click="removeItem($index)">X</button></td>
               </tr>
             </table>
           </div>
          
           <hr>
          <div class="form-group">
             <div class="col-md-3">
               <button id=btnVoidLastTxn ng-click="void_last_transaction()">
                 VOID LAST TRANSACTION
               </button>
               <span class="help-block help-block-label">Void Last Transaction</span>
            </div>
            <div class="col-md-2 product-inputs-left">
              <button id="btnCancelSale" ng-click="void_sale()">
                CANCEL SALE
              </button>
              <span class="help-block help-block-label">Void Entire Transaction</span>
            </div>
            <div class= "col-md-3">
                <button id="btnApplyEmpDiscount" onclick="history.back()" ng-click="checkout()">
                  Checkout
                </button>
                <span class="help-block help-block-label">Checkout</span>
            </div>
          </div>
          
      </div>
 </div>
    <script>
    angular.module('myApp', []).controller('itemsController', ['$scope', '$http', function($scope, $http) {
            //declaring global variables
            var discount_applied = false;
            

            //initializing view-controller variables
            $scope.cart = [];
            $scope.discount_row = [];

            //Populating lists
            $scope.product_list = [
                {code: '0001', quantity: 1, label: 'cereal_bag', name: 'Heinz Cereal Bag'},
                {code: '0002', quantity: 1, label: 'cereal_box', name: 'Gerber Cereal Box'},
                {code: '0003', quantity: 1, label: 'similac_aimentum', name: 'Similac Alimentum for Allergies & Colic'},
                {code: '0004', quantity: 1, label: 'similac_advanced', name: 'Similac Advanced (6-24 Months)'},
                {code: '0005', quantity: 1, label: 'baby_pacifier', name: 'Baby Pacifier'},
                {code: '0006', quantity: 1, label: 'enfamily_12months', name: 'Enfamil A+ Baby Formula 0-12 months'},
                {code: '0007', quantity: 1, label: 'enfagrow_toddler', name: 'Enfamil A+ Toddler Nutritional Drink (1-5 years)'},
                {code: '0008', quantity: 1, label: 'nestle_baby_formula', name: 'NESTLE GOOD START PLUS 2 Powder Baby Formula For Babies 6+ Months'},
                {code: '0009', quantity: 1, label: 'good_start_formula', name: 'GOOD START PLUS 1 Powder Baby Formula For Babies 0+'},
                {code: '0010', quantity: 1, label: 'tide_pods', name: 'Tide Pods'},
                {code: '0011', quantity: 1, label: 'dishwashing', name: 'Dishashing Liquid'},
                {code: '0012', quantity: 1, label: 'mini_shampoo', name: 'Mini Shampoo'},
                {code: '0013', quantity: 1, label: 'shampoo', name: 'Shampoo'},
                {code: '0014', quantity: 1, label: 'mini_conditioner', name: 'Mini Conditioner'},
                {code: '0015', quantity: 1, label: 'conditioner', name: 'Conditioner'},
                {code: '0016', quantity: 1, label: 'diapers_newborn', name: 'Diapers (Newborn)'},
                {code: '0017', quantity: 1, label: 'diapers_1', name: 'Diapers (Size 1)'},
                {code: '0018', quantity: 1, label: 'diapers_2', name: 'Diapers (Size 2)'},
                {code: '0019', quantity: 1, label: 'diapers_3', name: 'Diapers (Size 3)'},
                {code: '0020', quantity: 1, label: 'diapers_4', name: 'Diapers (Size 4)'},
                {code: '0021', quantity: 1, label: 'diapers_5', name: 'Diapers (Size 5)'},
                {code: '0022', quantity: 1, label: 'diapers_6', name: 'Diapers (Size 6)'},
                {code: '0023', quantity: 1, label: 'pull_ups', name: 'Pull Ups (Size 5T - 6T)'},
                {code: '0024', quantity: 1, label: 'sanitary_pad', name: 'Sanitary Pads'},
                {code: '0025', quantity: 1, label: 'defense_underwearsm', name: 'Defense Underwear (S/M)'},
                {code: '0026', quantity: 1, label: 'defense_underwearl', name: 'Defense Underwear (L)'},
                {code: '0027', quantity: 1, label: 'baby_wipes', name: 'Baby Wipes'},
                {code: '0028', quantity: 1, label: 'baby_wash', name: 'Baby Body Wash'},
                {code: '0029', quantity: 1, label: 'lotion', name: 'Body Lotion'},
                {code: '0029', quantity: 1, label: 'bottle9oz', name: '9oz Bottles'},
                {code: '0030', quantity: 1, label: 'bottle3oz', name: '5oz Bottles'},
                {code: '0031', quantity: 1, label: 'fruit_snack', name: 'Fruit Snack'},
                {code: '0032', quantity: 1, label: 'enfamil_nippe', name: 'Enfamil Soft Nipple'},
                {code: '0033', quantity: 1, label: 'enfamil_nipple', name: 'Enfamil Liquid Vitamin Supplement'},
                {code: '0034', quantity: 1, label: 'soap', name: 'Soap Bar'}
            ];
            $scope.employee_list = [
                {code: '1', name: 'Bill Gates', discount: 20},
                {code: '2', name: 'Steve Jobs', discount: 25},
                {code: '3', name: 'Alex Atwell', discount: 21},
                {code: '4', name: 'Andre Hyland', discount: 23},
                {code: '5', name: 'Romario Bates', discount: 20},
                {code: '6', name: 'Kwabena Adu', discount: 28}
            ];

            //Declaring view controller methods

            //This method searches for a NAME in a generic dictionary LIST
            $scope.search_list = function(name, list) {
                var item = [];
                for (var i in list) {
                    if (name == list[i].name) {
                        item = list[i];
                        break;
                    }
                }
                return item;
            };
            
            $scope.removeItem = function(index) {
                $scope.cart.splice(index, 1);
            };

            // This method is called when the user inputs a product name
            // It creates an object containing a single product's data
            $scope.select_product = function() {
                $scope.product = $scope.search_list($scope.inputed_product_name, $scope.product_list);
            };

            //This method updates the product object when user enters quantity needed
            $scope.select_quantity = function() {
                $scope.product.quantity = $scope.inputed_product_quantity;
            };

            //This method puts the product object into the cart and then reinitializes the product as well as all the data the user inputted
            $scope.add_to_cart = function() {
                if ($scope.product.name != undefined && $scope.product.quantity > 0) {
                    $scope.cart.push(angular.copy($scope.product));
                    $scope.product = undefined;
                    $scope.inputed_product_name = "";
                    $scope.inputed_product_quantity = "";
                }
            };

            //This method removes the last entered product from the cart
            $scope.void_last_transaction = function() {
                $scope.cart.pop();
            };

            //This method empties the cart incl. the discounts applied
            $scope.void_sale = function() {
                $scope.cart = [];
                $scope.discount_row = [];
                $scope.product = undefined;
            };

            //This method applies the employee discount
            $scope.apply_discount = function() {
                if ($scope.purchasing_employee.name != undefined) {
                    $scope.discount_row.push($scope.purchasing_employee);
                    discount_applied = true;
                }
            };

            //This method calculates the overall bill
            $scope.total = function() {
                if ($scope.cart.length > 0) {
                    var sum = 0;
                    var empDiscount = 0;
                    if (discount_applied) {
                        empDiscount += $scope.discount_row[0].discount;
                    }
                    sum *= 1 - empDiscount / 100;
                }
                return sum;
            };
            $scope.checkout = function() {
                // Assuming you have an array of items in the cart
                var cartItems = $scope.cart;

                // Assuming you have a discount row
                var discountRow = $scope.discount_row;

                // Assuming you have a product
                var product = $scope.product;

                // Create an object to hold the parameters
                var params = {
                    cartItems: cartItems,
                    discountRow: discountRow,
                    product: product
                };

                // Send the parameters to the Java function using an HTTP request
                // You can use AngularJS's $http service to make the request
                $http.post('http://172.16.105.6:8080/Immigration_Tracker/checkout', params)
                    .then(function(response) {
                        // Handle the response from the server
                        console.log("Checkout successful:", response.data);
                        // For example, you could clear the cart after checkout
                        $scope.cart = [];
                        $scope.discount_row = [];
                        $scope.product = undefined;
                    })
                    .catch(function(error) {
                        console.error("Error during checkout:", error);
                    });
            };
        }]);
    </script>
</div>
</body>
</html>

