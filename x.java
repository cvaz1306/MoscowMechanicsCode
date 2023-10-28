public class x{
    float cX;
    float cY;
    float cRotation;
    float distanceStrafed;
    float rightWheel, leftWheel, middleWheel, wheelDistance;
    float theta(){
       return (rightWheel/leftWheel)/wheelDistance;
    }
    float getX(){
        return cX+((middleWheel/theta())*Math.sin((theta())))*Math.cos(cRotation+(theta()/2))+middleWheel*Math.cos(cRotation-(Math.PI/2)+theta()/2);
    }
    float getY(){
        return cY+((middleWheel/theta())*Math.sin((theta())))/Math.cos(cRotation+(theta()/2))+middleWheel*Math.sin(cRotation-(Math.PI/2)+theta()/2);

    }
    float getRotation(){
        return cRotation+theta();
    }
    void main(){
        cX=getX();
        cY=getY();
        cRotation=getRotation();
    }
}