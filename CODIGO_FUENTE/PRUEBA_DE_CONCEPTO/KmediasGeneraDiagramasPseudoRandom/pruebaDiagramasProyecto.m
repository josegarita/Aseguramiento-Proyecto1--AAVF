x = importdata('puntosx.txt')
y = importdata('puntosy.txt')
scatter(x,y)

% clear all;
% 
% %load excel file 65 arrays with 64 datapoints for each array
% 
% [num, txt, raw] = xlsread ('datax.xlsx');
% 
% %write raw data to .dat file
% 
% dlmwrite('xdata.dat',raw, ' ')
% 
% % load .dat file
% 
% load xdata.dat