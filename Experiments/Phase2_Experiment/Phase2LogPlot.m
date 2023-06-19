clc 
clear all

% NOTE 1 TO EXAMINER: The error list is the most importnat data set here.
% This is later plotted as a loglog plot as it can be seen in line 37 to 42
% 
% NOTE 2 TO EXAMINER: Refere to experiments.java for how to experimenets were conducted

%-------------------------------------------------------------------
StepTime2 = [0.01 0.1 0.2 0.5 1];

%Exact values calculated by the equation y(t) = 2/(3-2t) - t
% Subiutited t with the values in StepTime2
ExactValues = [0.6611 0.6143 0.5692 0.5000 1];
%-------------------------------------------------------------------

%Euleurs Method. 
%The value outputed and the Abs. ERROR
eulers_Values2 = [0.9580556827956042 0.8860388526474804 0.5318456331548741 0.28395061728395055 0.11111111111111105];
Euleurs_Error2 = [0.04194431720439784 0.8860388526474804 0.4681543668451259 0.7160493827160495 0.888888888888889];

%Ralstons Method. 
% The value outputed and the Abs. ERROR
ralstons_Values2 = [0.9996516096246895 1.3420126491345439 0.9108117882628561  0.7088822598144533  0.4732510288065842];
Raltons_Error2 =[3.4839037531253503E-4 0.05798735086545537 0.08918821173714386 0.2911177401855467 0.5267489711934158];

%Heun's Method. 
% The value outputed and the Abs. ERROR
heuns_Values2 = [0.9999969186047991 1.3941327537057187 0.9851013155308098 0.8928919092801442 0.7006578968000837];
Heuns_Error2 = [.0813952028863056E-6 0.0058672462942805925 0.014898684469190182 0.10710809071985583 0.2993421031999163];


%Runge-Kutta4 Method. 
% The value outputed and the Abs. ERROR
rk4_Values2 = [0.9999999969565867 1.3998823687697413 0.9994296452427601 0.9870749127202167 0.9211740431776771];
RK4_Error2 = [3.043415297554475E-9 1.1763123025798627E-4 5.70354757239877E-4 0.012925087279783254 0.07882595682232285];
%--------------------------------------------------------------------------------------------------------------------------------------------%



loglog(StepTime2, Euleurs_Error2, 'k', StepTime2, Raltons_Error2, 'b', StepTime2, Heuns_Error2, 'r', StepTime2, RK4_Error2, 'm')
legend('Euler','Ralston','Heuns','RK4','Location','southeast')
grid on
title('Comparing Errors Of The Different Solvers') 
xlabel('Step Time') 
ylabel('Error')