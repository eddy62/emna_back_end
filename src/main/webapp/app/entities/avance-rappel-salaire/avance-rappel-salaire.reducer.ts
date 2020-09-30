import axios from 'axios';
import { ICrudDeleteAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { FAILURE, REQUEST, SUCCESS } from 'app/shared/reducers/action-type.util';

import { defaultValue, IAvanceRappelSalaire } from 'app/shared/model/avance-rappel-salaire.model';

export const ACTION_TYPES = {
  FETCH_AVANCERAPPELSALAIRE_LIST: 'avanceRappelSalaire/FETCH_AVANCERAPPELSALAIRE_LIST',
  FETCH_AVANCERAPPELSALAIRE: 'avanceRappelSalaire/FETCH_AVANCERAPPELSALAIRE',
  CREATE_AVANCERAPPELSALAIRE: 'avanceRappelSalaire/CREATE_AVANCERAPPELSALAIRE',
  UPDATE_AVANCERAPPELSALAIRE: 'avanceRappelSalaire/UPDATE_AVANCERAPPELSALAIRE',
  DELETE_AVANCERAPPELSALAIRE: 'avanceRappelSalaire/DELETE_AVANCERAPPELSALAIRE',
  RESET: 'avanceRappelSalaire/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAvanceRappelSalaire>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AvanceRappelSalaireState = Readonly<typeof initialState>;

// Reducer

export default (state: AvanceRappelSalaireState = initialState, action): AvanceRappelSalaireState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_AVANCERAPPELSALAIRE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_AVANCERAPPELSALAIRE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_AVANCERAPPELSALAIRE):
    case REQUEST(ACTION_TYPES.UPDATE_AVANCERAPPELSALAIRE):
    case REQUEST(ACTION_TYPES.DELETE_AVANCERAPPELSALAIRE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_AVANCERAPPELSALAIRE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_AVANCERAPPELSALAIRE):
    case FAILURE(ACTION_TYPES.CREATE_AVANCERAPPELSALAIRE):
    case FAILURE(ACTION_TYPES.UPDATE_AVANCERAPPELSALAIRE):
    case FAILURE(ACTION_TYPES.DELETE_AVANCERAPPELSALAIRE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_AVANCERAPPELSALAIRE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_AVANCERAPPELSALAIRE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_AVANCERAPPELSALAIRE):
    case SUCCESS(ACTION_TYPES.UPDATE_AVANCERAPPELSALAIRE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_AVANCERAPPELSALAIRE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/avance-rappel-salaires';

// Actions

export const getEntities: ICrudGetAllAction<IAvanceRappelSalaire> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_AVANCERAPPELSALAIRE_LIST,
  payload: axios.get<IAvanceRappelSalaire>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAvanceRappelSalaire> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_AVANCERAPPELSALAIRE,
    payload: axios.get<IAvanceRappelSalaire>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAvanceRappelSalaire> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_AVANCERAPPELSALAIRE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAvanceRappelSalaire> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_AVANCERAPPELSALAIRE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAvanceRappelSalaire> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_AVANCERAPPELSALAIRE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
