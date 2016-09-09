
function p = pearson(x,y)
    
    n = length(x);
    
    media_x = media(x,n);
    media_y = media(y,n);
    
    pearsonValue = 0;
    std_x = 0;
    std_y = 0;
    
    for index =  1:n
       
        diffX = x(index) - media_x;
        diffY = y(index) - media_y;
        
        pearsonValue = pearsonValue + (diffX * diffY);
        
        std_x = std_x + (diffX^2);
        std_y = std_y + (diffY^2);
        
    end
    
    p = pearsonValue / sqrt(std_x * std_y);
    
end



function m = media(x,n)
    m = sum(x) / n;
end


