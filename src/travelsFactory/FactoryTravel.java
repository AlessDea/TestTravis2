package travelsFactory;

import entities.Viaggio;
import entities.ViaggioGruppo;

public class FactoryTravel {

	public TravelBase createProductBase(int type) throws Exception{
        switch (type)
        {
        	case 1: return new Viaggio();
            case 2: return new ViaggioGruppo();
            default: throw new Exception("Invalid type : " + type);
        }
	}

	public TravelBase createConcreteViaggio(){
		return new Viaggio();
	
	}
	
	public TravelBase createConcreteViaggioGr(){
		return new ViaggioGruppo();
	}
}
