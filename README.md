
## Create order





As a Rest Client
I want to retrieve orders
So I can display simple customers’ orders
Given
A customer has submitted an order for some bricks
When
A Get Order request is submitted with a valid Order reference
Then
the order details are returned
and the order details contains the Order reference and the number of bricks ordered
When
A Get Order request is submitted with an invalid Order reference
Then
no order details are returned
Given
Many customer have submitted orders for bricks
When
A Get Orders request is submitted
Then
all the orders details are returned
and the order details contains the Order reference and the number of bricks ordered
Stage 2
As a Rest Client
I want to update orders for bricks
So I can update customers’ orders
Given
A customer has ordered a number of bricks
When
A Update Order request for an existing order reference and a number of bricks is submitted
Then
an Order reference the returned
And the Order reference is unique to the submission
Stage 3
As a Rest Client
I want to note when orders have been dispatched
So I can manage when orders are fulfilled
Given
An order exists
When
A Fulfil Order request is submitted for a valid Order reference
Then
the Order is marked as dispatched
Given
An order exists
When
A Fulfil Order request is submitted for a invalid Order reference
Then
a 400 bad request response is returned
Stage 4
As a Rest Client
I want the prevent updates to an order, when that order has been dispatched
So I don't accept updates to orders that have already shipped
Given
An order exists
and that order has been dispatched
When
A Update Order request is submitted for a valid Order reference
Then
a 400 bad request response is returned