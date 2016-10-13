plot(numFallasCriticas)
plot(numFallasMayores)
plot(numFallasMenores)
plot(numFallasTriviales)
plot(numFuncs)
plot(numLineas)
plot(numCiclomatico)
plot(tReqs)
plot(tDisenio)
plot(tAuditoriaDis)
plot(tAuditoriaImp)

hist(numFallasCriticas)
hist(numFallasMayores)
hist(numFallasMenores)
hist(numFallasTriviales)
hist(numFuncs)
hist(numLineas)
hist(numCiclomatico)
hist(tReqs)
hist(tDisenio)
hist(tAuditoriaDis)
hist(tAuditoriaImp)

plot(numFallasCriticas,'.')
plot(numFallasMayores,'.')
plot(numFallasMenores,'.')
plot(numFallasTriviales,'.')
plot(numFuncs,'.')
plot(numLineas,'.')
plot(numCiclomatico,'.')
plot(tReqs,'.')
plot(tDisenio,'.')
plot(tAuditoriaDis,'.')
plot(tAuditoriaImp,'.')

cs_dis = cumsum(numFallasCriticas);
plot(cs_dis)
cs_dis = cumsum(numFallasMayores);
plot(cs_dis)
cs_dis = cumsum(numFallasMenores);
plot(cs_dis)
cs_dis = cumsum(numFallasTriviales);
plot(cs_dis)
cs_dis = cumsum(numFuncs);
plot(cs_dis)
cs_dis = cumsum(numLineas);
plot(cs_dis)
cs_dis = cumsum(numCiclomatico);
plot(cs_dis)
cs_dis = cumsum(tReqs);
plot(cs_dis)
cs_dis = cumsum(tDisenio);
plot(cs_dis)
cs_dis = cumsum(tAuditoriaDis);
plot(cs_dis)
cs_dis = cumsum(tAuditoriaImp);
plot(cs_dis)

corrcoef(numCiclomatico,numFallasTriviales)
corrcoef(numCiclomatico,numFallasCriticas)
corrcoef(numCiclomatico,numFallasMayores)
corrcoef(numCiclomatico,numFallasMenores)


