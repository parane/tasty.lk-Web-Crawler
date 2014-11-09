
public class Review {
private String review;
private String resturant;
private String address;
public Review(String review, String resturant,String address)
{
	this.review=review;
	this.resturant=resturant;
	this.address= address;
	
}
public String getReview()
{
	return review;
}

public String getResturant()
{
	return resturant;
}

public String getAddress()
{
	return address;
}

public boolean equals(Object c) {
    if(!(c instanceof Review)) {
        return false;
    }

    Review that = (Review)c;
    return this.review.equals(that.getReview()) && this.resturant.equals(that.getResturant());
}
}
